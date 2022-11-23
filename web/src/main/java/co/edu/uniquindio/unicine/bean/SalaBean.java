package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.servicios.AdministradorTeatroServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class SalaBean implements Serializable {

    @Getter @Setter
    private Sala sala;

    private boolean editar;

    @Autowired
    private AdministradorTeatroServicio administradorTeatroServicio;

    @Getter @Setter
    private List<Teatro> teatros;

    @Getter @Setter
    private List<Sala> salas;

    @Getter @Setter
    private List<Sala> salasSelecionadas;

    @PostConstruct
    public void init(){

        sala = new Sala();
        editar = false;
        salasSelecionadas = new ArrayList<>();
        teatros = administradorTeatroServicio.teatros();
        salas = administradorTeatroServicio.salas();
    }

    public void crearSala(){
        try {
            if (!editar){
                Distribucion distribucion = administradorTeatroServicio.obtenerDistribucion(1);
                sala.setDistribucion(distribucion);
                Teatro teatro = administradorTeatroServicio.obtenerTeatro(1);
                sala.setTeatro(teatro);
                Sala registro = administradorTeatroServicio.crearSala(sala);
                salas.add(registro);
                sala = new Sala();
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO,"Alerta","Se ha creado la sala correctamente");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean",fm);
            }else {
                administradorTeatroServicio.actualizarSala(sala);
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO,"Alerta","Se ha actualizado la sala correctamente");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean",fm);
            }
        }catch (Exception e){
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta",e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean",fm);
        }
    }

    public void eliminarSalas() {

        try {
            for (Sala s : salasSelecionadas){
                administradorTeatroServicio.eliminarSala(s.getCodigo());
                salas.remove(s);
            }
            salasSelecionadas.clear();
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta",e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean",fm);
        }
    }

    public String getMensajeBorar(){
        if (salasSelecionadas.isEmpty()){
            return "Borrar";
        }else {
            return salasSelecionadas.size() == 1 ? "Borrar 1 Elemto" : "Borrar"+salasSelecionadas.size()+ "Elementos";
        }
    }

    public String getMensajeCrear(){
        return editar ? "Editar Sala" : "Crear Sala";
    }

    public void seleccionarSala(Sala salaSelecionada){
        this.sala = salaSelecionada;
        editar = true;
    }

    public void crearSalaDialogo(){
        this.sala = new Sala();
        editar = false;
    }
}
