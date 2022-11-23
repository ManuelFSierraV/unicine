package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AdministradorServicio {

    //------login Administrador----------

    Administrador login (String email, String password) throws Exception;

    //------ Metodos CRUD ciudad --------

    Ciudad crearCiudad (Ciudad ciudad);

    Ciudad obtenerCiudad (Integer codigoCiudad) throws Exception;

    Ciudad actualizarCiudad (Ciudad ciudad) throws Exception;

    void eliminarCiudad (Integer codigoCiudad) throws Exception;

    List<Ciudad> ciudades();

    //----- Metodos CRUD administradores teatro -----

    AdministradorTeatro crearAdministradorTeatro (AdministradorTeatro administradorTeatro) throws Exception;

    AdministradorTeatro obtenerAdministradorTeatro (Integer codigoAdministradorTeatro) throws Exception;

    AdministradorTeatro actualizarAdministradorTeatro (AdministradorTeatro administradorTeatro) throws Exception;

    void eliminarAdministradorTeatro (Integer codigoAdministradorTeatro) throws Exception;

    List<AdministradorTeatro> administradoresDeteatro();

    //----- Metodos CRUD pelicula -----

    Pelicula crearPelicula (Pelicula pelicula) throws Exception;

    Pelicula obtenerPelicula (Integer codigoPelicula) throws Exception;

    Pelicula actualizarPelicula (Pelicula pelicula) throws Exception;

    void eliminarPelicula (Integer codigoPelicula) throws Exception;

    List<Pelicula> peliculas();

    //----- Metodos CRUD confiteria -----

    Confiteria crearConfiteria (Confiteria confiteria) throws Exception;

    Confiteria obtenerConfiteria (Integer codigoConfiteria) throws Exception;

    Confiteria actualizarConfiteria (Confiteria confiteria) throws Exception;

    void eliminarConfiteria (Integer codigoConfiteria) throws Exception;

    List<Confiteria> productosConfiteria();

    //----- Metodos CRUD cupones -----

    Cupon crearCupon (Cupon cupon);

    Cupon obtenerCupon (Integer codigoCupon) throws Exception;

    Cupon actualizarCupon (Cupon cupon) throws Exception;

    void eliminarCupon (Integer codigoCupon) throws Exception;

    List<Cupon> cupones();
}
