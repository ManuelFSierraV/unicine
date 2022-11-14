package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClienteServicio {

    // ----- login -----

    Cliente login (String email, String password) throws Exception;

    // ----- Metodos CRUD cliente -----

    Cliente registrarCliente (Cliente cliente) throws Exception;

    Cliente actualizarCliente (Cliente cliente) throws Exception;

    void eliminarCliente (String cedulaCliente) throws Exception;

    List<Cliente> listarClientes ();

    Cliente obtenerClientePorCedula (String cedula) throws Exception;

    // ------ Lista de compras ------

    void historialCompras (String cedulaCliente);

    // ------- Buscar Peliculas --------

    List<Pelicula> buscarPeliculaPorNombre (String nombre) throws Exception;

    List<Pelicula> buscarPeliculaPorGenero (Genero genero) throws Exception;

    // ------- Realizar compra -------

    Compra hacerCompra (String cedula, List<Boleta> boletas, List<CompraConfiteria> compraConfiterias, MedioPago medioPago, Integer cuponCodigo,Integer funcionCodigo) throws Exception;

    // -------- Redimir Cupon --------


    CuponCliente obtenerCuponSeleccionado(Cliente cliente,Integer codigo) throws Exception;


    boolean redimirCupon (Integer codigoCupon) throws Exception;

    //-------------------------------- Cambiar Contraseña ----------------------------

    boolean cambiarPassword(String email, String passwordNueva)throws Exception;

    //-------- funcionalidades ----------

    Solicitud crearSolicitud (Solicitud solicitud) throws Exception;

    void eliminarSolicitud (Integer codigoSolicitud) throws Exception;

    List<Solicitud> solicitudes();

    FechaEspecial crearFechaEspecial (FechaEspecial fechaEspecial) throws Exception;

    FechaEspecial actualizarFechaEspecial (FechaEspecial fechaEspecial) throws Exception;

    void eliminarFechaEspecial (Integer codigoFechaEspecial) throws Exception;

}
