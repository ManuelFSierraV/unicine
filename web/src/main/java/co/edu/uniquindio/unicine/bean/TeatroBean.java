package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.entidades.Teatro;
import co.edu.uniquindio.unicine.servicios.AdministradorServicio;
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
public class TeatroBean implements Serializable {

    @Getter @Setter
    private Teatro teatro;

    private boolean editar;

    @Autowired
    private AdministradorTeatroServicio administradorTeatroServicio;

    @Autowired
    private AdministradorServicio administradorServicio;

    @Getter @Setter
    private List<Ciudad> ciudades;

    @Getter @Setter
    private List<Teatro> teatros;

    @Getter @Setter
    private List<Teatro> teatrosSeleccionados;

    @PostConstruct
    public void init(){

        teatro = new Teatro();
        editar = false;
        teatrosSeleccionados = new ArrayList<>();
        ciudades = administradorServicio.ciudades();
        teatros = administradorTeatroServicio.teatros();
    }

    public void crearTeatro(){
        try {
            if (!editar){
                AdministradorTeatro administradorTeatro = administradorServicio.obtenerAdministradorTeatro(1);
                teatro.setAdministradorTeatro(administradorTeatro);
                Teatro registro = administradorTeatroServicio.crearTeatro(teatro);
                teatros.add(registro);
                teatro = new Teatro();
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO,"Alerta","Se ha creado el teatro correctamente");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean",fm);
            }else {
                administradorTeatroServicio.actualizarTeatro(teatro);
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO,"Alerta","Se ha actualizado el teatro correctamente");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean",fm);
            }

        }catch (Exception e){
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta",e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean",fm);
        }

    }

    public void eliminarTeatros() {

            try {
                for (Teatro t : teatrosSeleccionados){
                    administradorTeatroServicio.eliminarTeatro(t.getCodigo());
                    teatros.remove(t);
                }
                teatrosSeleccionados.clear();
            } catch (Exception e) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta",e.getMessage());
                FacesContext.getCurrentInstance().addMessage("mensaje_bean",fm);
            }
    }

    public String getMensajeBorar(){
        if (teatrosSeleccionados.isEmpty()){
            return "Borrar";
        }else {
            return teatrosSeleccionados.size() == 1 ? "Borrar 1 Elemto" : "Borrar"+teatrosSeleccionados.size()+ "Elementos";
        }
    }

    public String getMensajeCrear(){
        return editar ? "Editar Teatro" : "Crear Teatro";
    }

    public void seleccionarteatro(Teatro teatroSelecionado){
        this.teatro = teatroSelecionado;
        editar = true;
    }

    public void crearTeatroDialogo(){
        this.teatro = new Teatro();
        editar = false;
    }
}
