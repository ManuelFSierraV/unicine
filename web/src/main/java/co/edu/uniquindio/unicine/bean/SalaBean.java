package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Sala;
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

@Component
@ViewScoped
public class SalaBean implements Serializable {

    @Getter @Setter
    private Sala sala;

    @Autowired
    private AdministradorTeatroServicio administradorTeatroServicio;

    @PostConstruct
    public void init(){
        sala = new Sala();
    }

    public void crearSala(){
        try {
            administradorTeatroServicio.crearSala(sala);
        }catch (Exception e){
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO,"Alerta",e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean",fm);
        }
    }
}
