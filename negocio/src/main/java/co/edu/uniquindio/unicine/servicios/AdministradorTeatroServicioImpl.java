package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministradorTeatroServicioImpl implements AdministradorTeatroServicio{

    @Autowired
    private final AdministradorTeatroRepo administradorTeatroRepo;
    private final HorarioRepo horarioRepo;
    private final TeatroRepo teatroRepo;
    private final SalaRepo salaRepo;
    private final FuncionRepo funcionRepo;
    private final PeliculaRepo peliculaRepo;
    private final DistribucionRepo distribucionRepo;

    public AdministradorTeatroServicioImpl(AdministradorTeatroRepo administradorTeatroRepo, HorarioRepo horarioRepo, TeatroRepo teatroRepo, SalaRepo salaRepo, FuncionRepo funcionRepo, PeliculaRepo peliculaRepo, DistribucionRepo distribucionRepo) {
        this.administradorTeatroRepo = administradorTeatroRepo;
        this.horarioRepo = horarioRepo;
        this.teatroRepo = teatroRepo;
        this.salaRepo = salaRepo;
        this.funcionRepo = funcionRepo;
        this.peliculaRepo = peliculaRepo;
        this.distribucionRepo = distribucionRepo;
    }

    @Override
    public AdministradorTeatro login(String email, String password) throws Exception {
        AdministradorTeatro administrador = administradorTeatroRepo.comprobarAutenticacion(email, password);

        if(administrador == null){
            throw new Exception("Los Datos de Autentificacion no son validos");
        }
        return administrador;

    }

    @Override
    public Teatro crearTeatro(Teatro teatro) throws Exception {

        boolean teatroExiste = teatroRepetido(teatro.getNombre());
        if(teatroExiste){
            throw new Exception("El teatro ya Existe");
        }
        return teatroRepo.save(teatro);
    }

    public boolean teatroRepetido(String nombreTeatro){
        return teatroRepo.findByNombre(nombreTeatro).orElse(null) != null;

    }

    @Override
    public Teatro obtenerTeatro(Integer codigoTeatro) throws Exception{

        Optional<Teatro> teatroGuardado = teatroRepo.findById(codigoTeatro);

        if(teatroGuardado.isEmpty()){
            throw new Exception("El cliente NO EXISTE");
        }

        return teatroGuardado.get();
    }

    @Override
    public Teatro actualizarTeatro(Teatro teatro) throws Exception {

        Optional<Teatro> teatroGuardado = teatroRepo.findById(teatro.getCodigo());
        if (teatroGuardado.isEmpty()){
            throw new Exception("El teatro NO EXISTE");
        }
        return teatroRepo.save(teatroGuardado.get());

    }

    @Override
    public void eliminarTeatro(Integer codigoTeatro) throws Exception {

        Optional<Teatro> teatroGuardado = teatroRepo.findById(codigoTeatro);
        if (teatroGuardado.isEmpty()){
            throw new Exception("El teatro NO EXISTE");
        }
        teatroRepo.delete(teatroGuardado.get());
    }

    @Override
    public List<Teatro> teatros() {
        return teatroRepo.findAll();
    }

    @Override
    public Horario crearHorario(Horario horario) throws Exception {

        boolean horarioExiste = horarioRepetido(horario.getCodigo());
        if(horarioExiste){
            throw new Exception("El horario ya EXISTE ");
        }
        return horarioRepo.save(horario);
    }

    public boolean horarioRepetido(Integer codigoHorario) {
        return horarioRepo.findById(codigoHorario).orElse(null) != null;
    }

    @Override
    public Horario obtenerHorario(Integer codigoHorario) throws Exception {

            Optional<Horario> horario = horarioRepo.findById(codigoHorario);
            if(horario.isEmpty()){
                throw new Exception("No existe el horario con ese codigo");
            }
            return horario.get();
        }

    @Override
    public Horario actualizarHorario(Horario horario) throws Exception {

        Optional<Horario> horarioGuardado = horarioRepo.findById(horario.getCodigo());
        if(horarioGuardado.isEmpty()){
            throw new Exception("El horario NO EXISTE");
        }
        return horarioRepo.save(horario);
    }

    @Override
    public void eliminarHorario(Integer codigoHorario) throws Exception {

        Optional<Horario> horarioGuardado = horarioRepo.findById(codigoHorario);
        if(horarioGuardado.isEmpty()){
            throw new Exception("El horario NO EXISTE");
        }
        horarioRepo.delete(horarioGuardado.get());
    }

    @Override
    public List<Horario> horarios() {
        return horarioRepo.findAll();
    }

    @Override
    public Funcion crearFuncion(Funcion funcion) throws Exception {

        boolean peliculaEnCartelera = peliculaEnCartelera(funcion.getPelicula().getNombre());

        if(!peliculaEnCartelera){
            throw new Exception("La pelicula no esta en cartelera");
        }
        boolean horarioExiste ;
        boolean salaExiste;
        return null;
    }

    public boolean horarioExiste (){
        return true;
    }

    public boolean peliculaEnCartelera(String nombre){
        Pelicula pelicula = peliculaRepo.buscarPeliculaPorNombre(nombre);
        if (pelicula.getEstado()== "Cartelera"){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Funcion obtenerFuncion(Integer codigoFuncion) throws Exception {

        Optional<Funcion> funcion = funcionRepo.findById(codigoFuncion);
        if(funcion.isEmpty()){
            throw new Exception("No existe la funcion con ese codigo");
        }
        return funcion.get();
    }

    @Override
    public Funcion actualizarFuncion(Funcion funcion) throws Exception {

        Optional<Funcion> funcionGuardada = funcionRepo.findById(funcion.getCodigo());
        if(funcionGuardada.isEmpty()){
            throw new Exception("La funcion NO EXISTE");
        }
        return funcionRepo.save(funcion);

    }

    @Override
    public void eliminarFuncion(Integer codigoFuncion) throws Exception {

        Optional<Funcion> funcionGuardada = funcionRepo.findById(codigoFuncion);
        if(funcionGuardada.isEmpty()){
            throw new Exception("La funcion NO EXISTE");
        }
        funcionRepo.delete(funcionGuardada.get());
    }

    @Override
    public List<Funcion> funciones() {
        return funcionRepo.findAll();
    }

    @Override
    public Sala crearSala(Sala sala) {
        return salaRepo.save(sala);
    }

    @Override
    public Sala obtenerSala(Integer codigoSala) throws Exception {

        Optional<Sala> sala = salaRepo.findById(codigoSala);
        if(sala.isEmpty()){
            throw new Exception("No existe la sala con ese codigo");
        }
        return sala.get();
    }

    @Override
    public Sala actualizarSala(Sala sala) throws Exception {

        Optional<Sala> salaGuardada = salaRepo.findById(sala.getCodigo());
        if(salaGuardada.isEmpty()){
            throw new Exception("La sala NO EXISTE");
        }
        return salaRepo.save(sala);
    }

    @Override
    public void eliminarSala(Integer codigoSala) throws Exception {

        Optional<Sala> salaGuardada = salaRepo.findById(codigoSala);
        if(salaGuardada.isEmpty()){
            throw new Exception("La sala NO EXISTE");
        }
        salaRepo.delete(salaGuardada.get());
    }

    @Override
    public List<Sala> salas() {
        return salaRepo.findAll();
    }

    @Override
    public Distribucion crearDistribucion(Distribucion distribucion) {
        return distribucionRepo.save(distribucion);
    }

    @Override
    public Distribucion obtenerDistribucion(Integer codigoDistribucion) throws Exception {
        Optional<Distribucion> distribucion = distribucionRepo.findById(codigoDistribucion);
        if(distribucion.isEmpty()){
            throw new Exception("No existe la distribucion con ese codigo");
        }
        return distribucion.get();
    }

}
