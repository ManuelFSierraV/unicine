package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import co.edu.uniquindio.unicine.entidades.Genero;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import co.edu.uniquindio.unicine.entidades.Teatro;
import co.edu.uniquindio.unicine.servicios.AdministradorServicio;
import co.edu.uniquindio.unicine.servicios.CloudinaryServicio;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

@Component
@ViewScoped
public class PeliculaBean implements Serializable {

    @Getter @Setter
    private Pelicula pelicula;

    private boolean editar;

    @Autowired
    private AdministradorServicio administradorServicio;

    @Autowired
    private CloudinaryServicio cloudinaryServicio;

    @Getter @Setter
    private List<Genero> generos;

    @Getter @Setter
    private List<Pelicula> peliculas;

    @Getter @Setter
    private List<Pelicula> peliculasSelecionadas;

    private Map<String,String> imagenes;

    @PostConstruct
    public void init(){

        pelicula = new Pelicula();
        editar = false;
        peliculasSelecionadas = new ArrayList<>();
        generos = Arrays.asList(Genero.values());
        imagenes = new HashMap<>();
        peliculas = administradorServicio.peliculas();
    }

    public void crearPelicula(){
        try {
            if (!editar){
                Pelicula registro = administradorServicio.crearPelicula(pelicula);
                peliculas.add(registro);
                pelicula = new Pelicula();
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO,"Alerta","Se ha creado la pelicula correctamente");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean",fm);
                if (!imagenes.isEmpty()){
                    pelicula.setImagenes(imagenes);
                }else {
                    FacesMessage fm1 = new FacesMessage(FacesMessage.SEVERITY_INFO,"Alerta","Es necesario subir al menos una imagen");
                    FacesContext.getCurrentInstance().addMessage("mensaje_bean",fm1);
                }
            }else {
                administradorServicio.actualizarPelicula(pelicula);
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO,"Alerta","Se ha actualizado la pelicula correctamente");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean",fm);
            }
        }catch (Exception e){
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta",e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean",fm);
        }
    }

    public void eliminarPeliculas() {

        try {
            for (Pelicula p : peliculasSelecionadas){
                administradorServicio.eliminarPelicula(p.getCodigo());
                peliculas.remove(p);
            }
            peliculasSelecionadas.clear();
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta",e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean",fm);
        }
    }

    public String getMensajeBorar(){
        if (peliculasSelecionadas.isEmpty()){
            return "Borrar";
        }else {
            return peliculasSelecionadas.size() == 1 ? "Borrar 1 Elemto" : "Borrar"+peliculasSelecionadas.size()+ "Elementos";
        }
    }

    public String getMensajeCrear(){
        return editar ? "Editar Pelicula" : "Crear Pelicula";
    }

    public void seleccionarPelicula(Pelicula peliculaSelecionada){
        this.pelicula = peliculaSelecionada;
        editar = true;
    }

    public void crearPeliculaDialogo(){
        this.pelicula = new Pelicula();
        editar = false;
    }

    public void subirImagenes(FileUploadEvent event){
        try {
            UploadedFile imagen = event.getFile();
            File imagenFile = convertirUploadedFile(imagen);
            Map resultado = cloudinaryServicio.subirImagen(imagenFile, "peliculas");
            imagenes.put(resultado.get("public_id").toString(),resultado.get("url").toString());
        }catch (Exception e){
            e.printStackTrace();
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta",e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean",fm);
        }
    }

    private File convertirUploadedFile(UploadedFile imagen) throws IOException {
        File file = new File(imagen.getFileName());
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(imagen.getContent());
        fos.close();
        return file;
    }
}
