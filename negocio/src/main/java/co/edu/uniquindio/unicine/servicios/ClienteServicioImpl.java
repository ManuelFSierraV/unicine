package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.dto.PeliculaFuncion;
import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicioImpl implements ClienteServicio{

    @Autowired
    private final ClienteRepo clienteRepo;
    private final PeliculaRepo peliculaRepo;
    private final EmailServicio emailServicio;
    private final SolicitudRepo solicitudRepo;
    private final FechaEspecialRepo fechaEspecialRepo;
    private final CuponRepo cuponRepo;
    private final CuponClienteRepo cuponClienteRepo;
    private final FuncionRepo funcionRepo;
    private final BoletaRepo boletaRepo;
    private final CompraConfiteriaRepo compraConfiteriaRepo;
    private final CompraRepo compraRepo;

    public ClienteServicioImpl(ClienteRepo clienteRepo, PeliculaRepo peliculaRepo, EmailServicio emailServicio, SolicitudRepo solicitudRepo, FechaEspecialRepo fechaEspecialRepo, CuponRepo cuponRepo, CuponClienteRepo cuponClienteRepo, FuncionRepo funcionRepo, BoletaRepo boletaRepo, CompraConfiteriaRepo compraConfiteriaRepo, CompraRepo compraRepo) {
        this.clienteRepo = clienteRepo;
        this.peliculaRepo = peliculaRepo;
        this.emailServicio = emailServicio;
        this.solicitudRepo = solicitudRepo;
        this.fechaEspecialRepo = fechaEspecialRepo;
        this.cuponRepo = cuponRepo;
        this.cuponClienteRepo = cuponClienteRepo;
        this.funcionRepo = funcionRepo;
        this.boletaRepo = boletaRepo;
        this.compraConfiteriaRepo = compraConfiteriaRepo;
        this.compraRepo = compraRepo;
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
        Optional<Pelicula> peliculaGuardada = peliculaRepo.findByNombre(nombre);

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

    @Override
    public List<Pelicula> buscarPeliculaPorEstadoCiudad (Integer codigoCiudad, String estado) throws Exception {
        List<Pelicula> peliculaGuardada = peliculaRepo.obtenerPeliculasPorEstadoCiudad(codigoCiudad,estado);

        if(peliculaGuardada.isEmpty()){
            throw new Exception("La pelicula NO EXISTE");
        }

        return peliculaGuardada;
    }

    @Override
    public List<Pelicula> buscarPeliculaPorEstado(String estado) {
        List<Pelicula> peliculaGuardada = peliculaRepo.obtenerPeliculasPorEstado(estado);
        return peliculaGuardada;
    }

    @Override
    public List<PeliculaFuncion> listarFuncionesPelicula(String nombre) throws Exception{
        List<PeliculaFuncion> peliculaGuardada = peliculaRepo.buscarFuncionPelicula(nombre);

        if(peliculaGuardada.isEmpty()){
            throw new Exception("La pelicula No existe");
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
        Cliente registro = clienteRepo.save(cliente);
        //emailServicio.enviarEmail("Registro de cuenta en UniCine", "Hola "+cliente.getNombre()+" ahora eres parte de la familia Unicine, para activar su cuenta click en el siguiente link: url", cliente.getEmail());
        return registro;
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
    public Compra hacerCompra(String cedula, List<Boleta> boletas, List<CompraConfiteria> compraConfiterias, MedioPago medioPago, Integer cuponCodigo,Integer funcionCodigo) throws Exception {

        Compra compra = new Compra();
        Optional<Cliente> cliente = clienteRepo.findById(cedula);
        Optional<Funcion> funcion = funcionRepo.findById(funcionCodigo);
        Double valorEntradas = 0.0;
        Double valorConfiterias = 0.0;
        Double descuentoCompra = 0.0;

        if (cliente == null) {
            throw new Exception("El cliente con la cedula" + cliente.get().getCedula() + "no existe");
        }
        if (funcion == null) {
            throw new Exception("La funcion con el codigo" + funcion.get().getCodigo() + "no existe");
        }
        if (medioPago == null) {
            throw new Exception("Elija un medio de pago disponible");
        }
        if (boletas.isEmpty()) {
            throw new Exception("La lista de boletas esta vacia");
        }

        boletas.forEach(entrada ->
                entrada.setCompra(compra)
        );
        compra.setBoletas(boletas);
        boletaRepo.saveAll(boletas);

        if (!compraConfiterias.isEmpty()) {
            for (int i = 0; i < compraConfiterias.size(); i++) {
                compraConfiterias.get(i).setCompra(compra);
                valorConfiterias += (compraConfiterias.get(i).getPrecio());
            }
            compra.getCompraConfiteriaList();
            compraConfiteriaRepo.saveAll(compraConfiterias);
        }

        if (cuponCodigo != null) {
            CuponCliente cupon = obtenerCuponSeleccionado(cliente.get(), cuponCodigo);
            if (cupon != null) {
                cupon.setCompra(compra);
                compra.setCuponCliente(cupon);
                cupon.setEstado(cupon.getEstado());
                cuponClienteRepo.save(cupon);

                Double porcentajeDescuento = (cupon.getCupon().getDescuento() / 100);
                descuentoCompra = (valorConfiterias + valorEntradas) * porcentajeDescuento;
            } else {
                throw new Exception("El cliente no posee un cupon con codigo " + cuponCodigo);
            }
        }


        for (int i = 0; i < boletas.size(); i++) {
            valorEntradas = (boletas.get(i).getPrecio()) + valorEntradas;
        }
        double valorTotal = (valorConfiterias + valorEntradas) - descuentoCompra;

        funcion.get().getCompras().add(compra);
        compra.setFuncion(funcion.get());
        compra.setMedioPago(medioPago);
        compra.setValorTotal(valorTotal);
        cliente.get().getCompras().add(compra);
        compra.setCliente(cliente.get());
        //compra.getFecha(LocalDate.now());
        //compra.setEntradas(entradas);

        clienteRepo.save(cliente.get());
        funcionRepo.save(funcion.get());
        boletaRepo.saveAll(boletas);
        compraConfiteriaRepo.saveAll(compraConfiterias);
        Compra compraGuardada = compraRepo.save(compra);

       // if (compraRepo.contarComprasCliente(cedulaCliente) == 0) {
            //Optional<Cupon> cuponPrimerCompra = cuponRepo.findById(2);
            //CuponCliente cuponCliente = CuponCliente.builder().cupon(cuponPrimerCompra).cliente(cliente).estado(EstadoCupon.SIN_USAR).build();
            //cuponClienteRepo.save(cuponCliente);
            //emailServicio.enviarEmail("Cupon primer compra", "Hola, has recibido un cupon del 10% por realizar tu primer compra, para obtenerlo ve al aiguiente enlace: ....", clienteCedula.getCorreo());
        //}

        //emailServicio.enviarEmail("Compra unicine", "Hola" + cliente.getNombre() + "has realizado una compra en unicine de los siguientes productos:" + compraGuardada.getCompraConfiterias() + "\n" + compraGuardada.getEntradas() + " \n" + compraGuardada.getFuncion() + "todo por un valor de $" + valorTotal, cliente.getCorreo());

        return compraGuardada;

    }

    //------------------------------------ REDMIR CUPON -----------------------------------


    @Override
    public CuponCliente obtenerCuponSeleccionado(Cliente cliente,Integer codigo) throws Exception {

        if(cliente.getCuponesCliente().isEmpty()){
            throw new Exception("La lista de cupones esta vacia "+codigo);
        }
        for (CuponCliente cupon: cliente.getCuponesCliente()) {
            if(cupon.getCodigo().equals(codigo)){
                return cupon;
            }
        }

        return null;
    }

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
