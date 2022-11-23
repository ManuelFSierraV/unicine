package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.servicios.AdministradorServicio;
import co.edu.uniquindio.unicine.servicios.AdministradorTeatroServicio;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@Component
@Scope("session")
public class SeguridadBean implements Serializable {

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private AdministradorServicio administradorServicio;

    @Autowired
    private AdministradorTeatroServicio administradorTeatroServicio;

    @Getter @Setter
    private boolean autenticado;

    @Getter @Setter
    private String email, password;

    @Getter @Setter
    private Cliente cliente;

    @Getter @Setter
    private Ciudad ciudadSelecionada;

    @PostConstruct
    public void init(){
        autenticado = false;
    }

    public String iniciarSesionCliente(){
        if (!email.isEmpty() && !password.isEmpty()){
            try {
                cliente = clienteServicio.login(email,password);
                autenticado = true;
                return "/index?faces-redirect = true";
            } catch (Exception e) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta",e.getMessage());
                FacesContext.getCurrentInstance().addMessage("login-bean",fm);
            }
        }else{
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta","Email y contraseña son oblogatorios");
            FacesContext.getCurrentInstance().addMessage("login-bean",fm);
        }
        return null;
    }
    public String iniciarSesionAdministrador(){
        if (!email.isEmpty() && !password.isEmpty()){
            try {
                administradorServicio.login(email,password);
                autenticado = true;
                return "/index?faces-redirect = true";
            } catch (Exception e) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta",e.getMessage());
                FacesContext.getCurrentInstance().addMessage("login-bean",fm);
            }
        }else{
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta","Email y contraseña son oblogatorios");
            FacesContext.getCurrentInstance().addMessage("login-bean",fm);
        }
        return null;
    }

    public String iniciarSesionAdministradorTeatro(){
        if (!email.isEmpty() && !password.isEmpty()){
            try {
                administradorTeatroServicio.login(email,password);
                autenticado = true;
                return "/index?faces-redirect = true";
            } catch (Exception e) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta",e.getMessage());
                FacesContext.getCurrentInstance().addMessage("login-bean",fm);
            }
        }else{
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta","Email y contraseña son oblogatorios");
            FacesContext.getCurrentInstance().addMessage("login-bean",fm);
        }
        return null;
    }

    public String cerrarSesion(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index?faces-redirect = true";
    }

    public void selecionarCiudad(Ciudad ciudad){
        this.ciudadSelecionada = ciudad;
    }
}
