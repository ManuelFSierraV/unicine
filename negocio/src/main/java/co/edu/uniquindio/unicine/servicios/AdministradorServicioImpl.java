package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministradorServicioImpl implements AdministradorServicio{

    @Autowired
    private final AdministradorRepo administradorRepo;
    private final CiudadRepo ciudadRepo;
    private final TeatroRepo teatroRepo;
    private final CuponRepo cuponRepo;
    private final PeliculaRepo peliculaRepo;
    private final ConfiteriaRepo confiteriaRepo;
    private final AdministradorTeatroRepo administradorTeatroRepo;

    public AdministradorServicioImpl(AdministradorRepo administradorRepo, CiudadRepo ciudadRepo, TeatroRepo teatroRepo, CuponRepo cuponRepo, PeliculaRepo peliculaRepo, ConfiteriaRepo confiteriaRepo, AdministradorTeatroRepo administradorTeatroRepo) {
        this.administradorRepo = administradorRepo;
        this.ciudadRepo = ciudadRepo;
        this.teatroRepo = teatroRepo;
        this.cuponRepo = cuponRepo;
        this.peliculaRepo = peliculaRepo;
        this.confiteriaRepo = confiteriaRepo;
        this.administradorTeatroRepo = administradorTeatroRepo;
    }


    @Override
    public Administrador login(String email, String password) throws Exception {

        Administrador administrador = administradorRepo.compraboarAuntenticacion(email, password);

        if(administrador == null){
            throw new Exception("Los Datos de Autentificacion son INCORRECTOS");
        }
        return administrador;

    }

    @Override
    public Ciudad crearCiudad(Ciudad ciudad) {
        return ciudadRepo.save(ciudad);
    }

    @Override
    public Ciudad obtenerCiudad(Integer codigoCiudad) throws Exception {

        Optional<Ciudad> ciudad = ciudadRepo.findById(codigoCiudad);
        if(ciudad.isEmpty()){
            throw new Exception("No existe la ciudad con ese codigo postal");
        }
        return ciudad.get();

    }

    @Override
    public Ciudad actualizarCiudad(Ciudad ciudad) throws Exception {

        Optional<Ciudad> ciudadGuardado = ciudadRepo.findById(ciudad.getCodigo());
        if(ciudadGuardado.isEmpty()){
            throw new Exception("La ciudad NO EXISTE");
        }
        return ciudadRepo.save(ciudad);
    }

    @Override
    public void eliminarCiudad(Integer codigoCiudad) throws Exception {

        Optional<Ciudad> ciudadGuardado = ciudadRepo.findById(codigoCiudad);
        if(ciudadGuardado.isEmpty()){
            throw new Exception("La ciudad NO EXISTE");
        }
        ciudadRepo.delete(ciudadGuardado.get());
    }

    @Override
    public List<Ciudad> ciudades() {
        return ciudadRepo.findAll();
    }
    public boolean ciudadRepetida(String nombreCiudad){
        return ciudadRepo.findByNombre(nombreCiudad).orElse(null)!= null;
    }


    @Override
    public AdministradorTeatro crearAdministradorTeatro(AdministradorTeatro administradorTeatro) throws Exception{

        boolean administradorTeatrosExiste = AdministradorRepetido(administradorTeatro.getCodigo());
        if(administradorTeatrosExiste){
            throw new Exception("La codigo para el administrador ya Existe");
        }
        boolean administradorTeatrosExisteCorreo = AdministradorRepetidoCorreo(administradorTeatro.getEmail()) ;
        if(administradorTeatrosExisteCorreo){
            throw new Exception("El correo para el administrador ya Existe");
        }
        return administradorTeatroRepo.save(administradorTeatro);

    }
    private boolean AdministradorRepetidoCorreo(String email){
        return administradorTeatroRepo.findByEmail(email).orElse(null)!=null;
    }

    private boolean AdministradorRepetido(Integer codigoAdministrador) {
        return administradorTeatroRepo.findByCodigo(codigoAdministrador).orElse(null) != null;
    }

    @Override
    public AdministradorTeatro obtenerAdministradorTeatro(Integer codigoAdministradorTeatro) throws Exception {

        Optional<AdministradorTeatro> administradorTeatro = administradorTeatroRepo.findById(codigoAdministradorTeatro);
        if(administradorTeatro.isEmpty()){
            throw new Exception("No existe un administrador de teatros con ese codigo");
        }
        return administradorTeatro.get();
    }

    @Override
    public AdministradorTeatro actualizarAdministradorTeatro(AdministradorTeatro administradorTeatro) throws Exception {

        Optional<AdministradorTeatro> administradorGuardado = administradorTeatroRepo.findById(administradorTeatro.getCodigo());
        if(administradorGuardado.isEmpty()){
            throw new Exception("El administrador NO EXISTE");
        }
        return administradorTeatroRepo.save(administradorTeatro);
    }

    @Override
    public void eliminarAdministradorTeatro(Integer codigoAdministradorTeatro) throws Exception {

        Optional<AdministradorTeatro> administradorGuardado = administradorTeatroRepo.findById(codigoAdministradorTeatro);
        if(administradorGuardado.isEmpty()){
            throw new Exception("El administrador NO EXISTE");
        }
        administradorTeatroRepo.delete(administradorGuardado.get());

    }

    @Override
    public List<AdministradorTeatro> administradoresDeteatro() {
        return administradorTeatroRepo.findAll();
    }

    @Override
    public Pelicula crearPelicula(Pelicula pelicula) throws Exception{

        Optional<Pelicula> peliculaNueva = peliculaRepo.findByNombre(pelicula.getNombre());

        if(peliculaNueva != null){
            throw new Exception("La pelicula con nombre "+pelicula.getNombre()+" ya se encuentra registrada");
        }

        return peliculaRepo.save(pelicula);
    }

    @Override
    public Pelicula obtenerPelicula(Integer codigoPelicula) throws Exception {

        Optional<Pelicula> pelicula = peliculaRepo.findById(codigoPelicula);
        if(pelicula.isEmpty()){
            throw new Exception("No existe la pelicula con ese codigo");
        }
        return pelicula.get();
    }

    @Override
    public Pelicula actualizarPelicula(Pelicula pelicula) throws Exception {

        Optional<Pelicula> peliculaGuardada = peliculaRepo.findById(pelicula.getCodigo());
        if (peliculaGuardada.isEmpty()){
            throw new Exception("La pelicula NO EXISTE");
        }
        return peliculaRepo.save(peliculaGuardada.get());

    }

    @Override
    public void eliminarPelicula(Integer codigoPelicula) throws Exception {

        Optional<Pelicula> peliculaGuardada = peliculaRepo.findById(codigoPelicula);
        if (peliculaGuardada.isEmpty()){
            throw new Exception("La pelicula NO EXISTE");
        }
        peliculaRepo.delete(peliculaGuardada.get());
    }

    @Override
    public List<Pelicula> peliculas() {
        return peliculaRepo.findAll();
    }

    @Override
    public Confiteria crearConfiteria(Confiteria confiteria) throws Exception{
        Optional<Confiteria> confiteriaEncontrado = confiteriaRepo.findById(confiteria.getCodigo());

        if(confiteriaEncontrado != null){
            throw new Exception("Ya existe una confiteria con el codigo "+confiteria.getCodigo());
        }

        return confiteriaRepo.save(confiteria);

    }

    @Override
    public Confiteria obtenerConfiteria(Integer codigoConfiteria) throws Exception {

        Optional<Confiteria> confiteria = confiteriaRepo.findById(codigoConfiteria);
        if(confiteria.isEmpty()){
            throw new Exception("No existe la confiteria con ese codigo");
        }
        return confiteria.get();

    }

    @Override
    public Confiteria actualizarConfiteria(Confiteria confiteria) throws Exception {

        Optional<Confiteria> confiteriaGuardada = confiteriaRepo.findById(confiteria.getCodigo());
        if (confiteriaGuardada.isEmpty()){
            throw new Exception("El producto NO EXISTE");
        }
        return confiteriaRepo.save(confiteriaGuardada.get());
    }

    @Override
    public void eliminarConfiteria(Integer codigoConfiteria) throws Exception {

        Optional<Confiteria> confiteriaGuardada = confiteriaRepo.findById(codigoConfiteria);
        if (confiteriaGuardada.isEmpty()){
            throw new Exception("El producto NO EXISTE");
        }
        confiteriaRepo.delete(confiteriaGuardada.get());
    }

    @Override
    public List<Confiteria> productosConfiteria() {
        return confiteriaRepo.findAll();
    }

    @Override
    public Cupon crearCupon(Cupon cupon) {
        return cuponRepo.save(cupon);
    }

    @Override
    public Cupon obtenerCupon(Integer codigoCupon) throws Exception {

        Optional<Cupon> cupon = cuponRepo.findById(codigoCupon);
        if(cupon.isEmpty()){
            throw new Exception("No existe el cupon con ese codigo postal");
        }
        return cupon.get();

    }

    @Override
    public Cupon actualizarCupon(Cupon cupon) throws Exception {

        Optional<Cupon> cuponGuardado = cuponRepo.findById(cupon.getCodigo());
        if(cuponGuardado.isEmpty()){
            throw new Exception("El cupon NO EXISTE");
        }
        return cuponRepo.save(cuponGuardado.get());
    }

    @Override
    public void eliminarCupon(Integer codigoCupon) throws Exception {

        Optional<Cupon> cuponGuardado = cuponRepo.findById(codigoCupon);
        if(cuponGuardado.isEmpty()){
            throw new Exception("El cupon NO EXISTE");
        }
        cuponRepo.delete(cuponGuardado.get());
    }

    @Override
    public List<Cupon> cupones() {
        return cuponRepo.findAll();
    }
}
