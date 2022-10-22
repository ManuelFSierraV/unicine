package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class ClienteServicioImpl implements ClienteServicio{

    @Autowired
    private final ClienteRepo clienteRepo;
    private final PeliculaRepo peliculaRepo;
    private final EmailServicio emailServicio;
    private final SolicitudRepo solicitudRepo;
    private final FechaEspecialRepo fechaEspecialRepo;
    private final CuponRepo cuponRepo;
    private final CuponClienteRepo cuponClienteRepo;

    public ClienteServicioImpl(ClienteRepo clienteRepo, PeliculaRepo peliculaRepo, EmailServicio emailServicio, SolicitudRepo solicitudRepo, FechaEspecialRepo fechaEspecialRepo, CuponRepo cuponRepo, CuponClienteRepo cuponClienteRepo) {
        this.clienteRepo = clienteRepo;
        this.peliculaRepo = peliculaRepo;
        this.emailServicio = emailServicio;
        this.solicitudRepo = solicitudRepo;
        this.fechaEspecialRepo = fechaEspecialRepo;
        this.cuponRepo = cuponRepo;
        this.cuponClienteRepo = cuponClienteRepo;
    }

    //------------------------------------LOGIN----------------------------------------
    @Override
    public Cliente login(String email, String password) throws Exception{
        Cliente cliente = clienteRepo.comprobarAutenticacion(email, password);

        if(cliente == null){
            throw new Exception("Los Datos de Autentificacion no son validos");
        }
        return cliente;
    }

    //----------------------------------- BUSCAR PELICULA -------------------------------
    @Override
    public List<Pelicula> buscarPeliculaPorNombre(String nombre) throws Exception {
        Optional<Pelicula> peliculaGuardada = peliculaRepo.findByNombrePelicula(nombre);

        if(peliculaGuardada.isEmpty()){
            throw new Exception("La pelicula NO EXISTE");
        }

        return (List<Pelicula>) peliculaGuardada.get();
    }

    @Override
    public List<Pelicula> buscarPeliculaPorGenero(Genero genero) throws Exception {
        List<Pelicula> peliculaGuardada = peliculaRepo.obtenerPeliculasPorGenero(genero);

        if(peliculaGuardada.isEmpty()){
            throw new Exception("La pelicula NO EXISTE");
        }

        return peliculaGuardada;
    }



    //---------------------------------- CRUD DE CLIENTE --------------------------------
    @Override
    public Cliente registrarCliente(Cliente cliente) throws Exception{

        boolean correoExiste = esRepetido(cliente.getEmail());
        if(correoExiste){
            throw new Exception("El Correo ya esta en Uso");
        }
        //cedula
        boolean cedulaExiste = cedulaRepetida(cliente.getCedula());
        if(cedulaExiste){
            throw  new Exception("La cedula ingresada ya existe");
        }

        emailServicio.enviarEmail("Registro de cuenta en UniCine", "Hola "+cliente.getNombre()+" ahora eres parte de la familia Unicine, para activar su cuenta click en el siguiente link: url", cliente.getEmail());
        return clienteRepo.save(cliente);
    }

    private boolean esRepetido(String email){
        return clienteRepo.findByEmail(email).orElse(null) != null;
    }

    private boolean cedulaRepetida(String cedula) {
        return clienteRepo.existsById(cedula);
    }
    @Override
    public Cliente obtenerClientePorCedula(String cedula) throws Exception {

        Optional<Cliente> clienteGuardado = clienteRepo.findById(cedula);

        if(clienteGuardado.isEmpty()){
            throw new Exception("El cliente NO EXISTE");
        }

        return clienteGuardado.get();
    }

    @Override
    public Cliente actualizarCliente(Cliente cliente) throws Exception{

        Optional<Cliente> clienteGuardado = clienteRepo.findById(cliente.getCedula());

        if (clienteGuardado.isEmpty()){
            throw new Exception("El cliente NO EXISTE");
        }
        return clienteRepo.save(cliente);
    }

    @Override
    public void eliminarCliente(String cedulaCliente) throws Exception{

        Optional<Cliente> clienteGuardado = clienteRepo.findById(cedulaCliente);

        if(clienteGuardado.isEmpty()){
            throw new Exception("El cliente NO EXISTE");
        }

        clienteRepo.delete(clienteGuardado.get());
    }

    @Override
    public List<Cliente> listarClientes() {
        return clienteRepo.findAll();
    }

    //------------------------------ LISTAR LA COMPRAS -------------------------------
    @Override
    public void historialCompras(String cedulaCliente){
        List<Compra> compras = clienteRepo.obtenerComprasCliente(cedulaCliente);
        compras.forEach(System.out::println);
    }

    //---------------------------------- HACER UNA COMPRA --------------------------------
    @Override
    public Compra hacerCompra(Compra compra, Boleta boletas, CompraConfiteria compraConfiteria, MedioPago medioPago, Cupon cupon, Funcion funcion) throws Exception {
        Compra compra1 = new Compra();
        compra.setFecha(LocalDateTime.now());

        //verificar cliente,

        //verificar que las sillas esten disponibles

        //redimir el cupon si no es null

        Optional<Cupon> cuponExiste = cuponRepo.findById(cupon.getCodigo());
        if (cuponExiste.isEmpty()){
            throw new Exception("El cupon no existe");
        }
        boolean cuponCliente =false ;


        //sumar los precios, aplicar el descuento

        //persiste la compra
        return null;
    }

    //------------------------------------ REDMIR CUPON -----------------------------------
    @Override
    public boolean redimirCupon(Integer codigoCupon) throws Exception{

        return false;
    }

    //------------------------------------- Cambiar Contraseña ------------------------------
    public void enviarLinkRecuperacion(String correo){
        emailServicio.enviarEmail("Recuperacion password", "Para recupear la contraseÃ±a ingrese a: []", correo);
    }

    @Override
    public boolean cambiarPassword(String email, String passwordNueva ) throws Exception {

        Cliente cliente = clienteRepo.findByEmail(email).orElse(null);
        enviarLinkRecuperacion(email);

        if(cliente==null){
            throw new Exception("El cliente no se encontro con el correo ingresado");
        }
        cliente.setPassword(passwordNueva);
        clienteRepo.save(cliente);

        return true;
    }

    @Override
    public Solicitud crearSolicitud(Solicitud solicitud) throws Exception {

        boolean solicitudBandera = solicitudRepetida(solicitud.getCodigo());
        if(solicitudBandera){
            throw new Exception("La solicitud ya Existe");
        }
        return solicitudRepo.save(solicitud);
    }

    public boolean solicitudRepetida(Integer codigoSolicitud){
        return solicitudRepo.findById(codigoSolicitud).orElse(null) != null;

    }

    @Override
    public void eliminarSolicitud(Integer codigoSolicitud) throws Exception{

        Optional<Solicitud> solicitudGuardada = solicitudRepo.findById(codigoSolicitud);

        if(solicitudGuardada.isEmpty()){
            throw new Exception("la solicitud NO EXISTE");
        }

        solicitudRepo.delete(solicitudGuardada.get());
    }

    @Override
    public List<Solicitud> solicitudes() {
        return solicitudRepo.findAll();
    }

    @Override
    public FechaEspecial crearFechaEspecial(FechaEspecial fechaEspecial) throws Exception {

        boolean fechaEspecialBandera = fechaEspecialRepetida(fechaEspecial.getCodigo());
        if(fechaEspecialBandera){
            throw new Exception("La solicitud ya Existe");
        }
        return fechaEspecialRepo.save(fechaEspecial);
    }

    public boolean fechaEspecialRepetida(Integer codigoFechaEspecial){
        return fechaEspecialRepo.findById(codigoFechaEspecial).orElse(null) != null;

    }

    @Override
    public FechaEspecial actualizarFechaEspecial(FechaEspecial fechaEspecial) throws Exception {
        Optional<FechaEspecial> fechaEspecialGuardada = fechaEspecialRepo.findById(fechaEspecial.getCodigo());

        if (fechaEspecialGuardada.isEmpty()){
            throw new Exception("El cliente NO EXISTE");
        }
        return fechaEspecialRepo.save(fechaEspecial);
    }

    @Override
    public void eliminarFechaEspecial(Integer codigoFechaEspecial) throws Exception{

        Optional<FechaEspecial> fechaEspecialGuardada = fechaEspecialRepo.findById(codigoFechaEspecial);

        if(fechaEspecialGuardada.isEmpty()){
            throw new Exception("la fecha especial NO EXISTE");
        }

        fechaEspecialRepo.delete(fechaEspecialGuardada.get());
    }


}
