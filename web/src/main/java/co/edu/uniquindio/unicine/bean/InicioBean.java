package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import co.edu.uniquindio.unicine.servicios.AdministradorServicio;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class InicioBean implements Serializable {

    @Autowired
    ClienteServicio clienteServicio;

    @Autowired
    AdministradorServicio administradorServicio;

    @Getter @Setter
    private List<Pelicula> peliculasCartelera;

    @Getter @Setter
    private List<Pelicula> peliculasProximamente;

    @Getter @Setter
    private List<Ciudad> ciudades;

    @Getter @Setter
    private List<String> imagenes;

    @Getter @Setter
    private Ciudad ciudad;

    public void init(){

        try {
            imagenes = new ArrayList<>();
            imagenes.add("https://res.cloudinary.com/dlvqmnerx/image/upload/v1669160039/peliculas/tileburnedin_dmby00.jpg");
            imagenes.add("https://res.cloudinary.com/dlvqmnerx/image/upload/v1669160573/peliculas/wf_tobpp_3000px_opt_lcg2er.jpg");
            imagenes.add("https://res.cloudinary.com/dlvqmnerx/image/upload/v1669160725/peliculas/91bsMwU7IzL._RI__a9vhxj.jpg");
            peliculasCartelera = clienteServicio.buscarPeliculaPorEstado("Cartelera");
            peliculasProximamente = clienteServicio.buscarPeliculaPorEstado("Proximamente");
            ciudades = administradorServicio.ciudades();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void elegirCiudad(){

            try {
                if (ciudad != null) {
                    peliculasCartelera = clienteServicio.buscarPeliculaPorEstadoCiudad(ciudad.getCodigo(), "Cartelera");
                    peliculasProximamente = clienteServicio.buscarPeliculaPorEstadoCiudad(ciudad.getCodigo(), "Proximamente");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
