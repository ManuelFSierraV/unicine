package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AdministradorTeatroServicio {

    //-------- Login -------

    AdministradorTeatro login (String email, String password) throws Exception;

    //-------- Metodos CRUD teatro ------

    Teatro crearTeatro (Teatro teatro) throws Exception;

    Teatro obtenerTeatro (Integer codigoTeatro) throws Exception;

    Teatro actualizarTeatro (Teatro teatro) throws Exception;

    void eliminarTeatro (Integer codigoTeatro) throws Exception;

    List<Teatro> teatros();

    //------- Metodos CRUD horario -------

    Horario crearHorario (Horario horario) throws Exception;

    Horario obtenerHorario (Integer codigoHorario) throws Exception;

    Horario actualizarHorario (Horario horario) throws Exception;

    void eliminarHorario (Integer codigoHorario) throws Exception;

    List<Horario> horarios ();

    // ------- Metodos CRUD funciones --------

    Funcion crearFuncion (Funcion funcion) throws Exception;

    Funcion obtenerFuncion (Integer codigoFuncion) throws Exception;

    Funcion actualizarFuncion (Funcion funcion) throws Exception;

    void eliminarFuncion (Integer codigoFuncion) throws Exception;

    List<Funcion> funciones();

    // ------- Metodos CRUD sala --------

    Sala crearSala (Sala sala);

    Sala obtenerSala (Integer codigoSala) throws Exception;

    Sala actualizarSala (Sala sala) throws Exception;

    void eliminarSala (Integer codigoSala) throws Exception;

    List<Sala> salas ();

    //--------- Distribucion -----------------

    Distribucion crearDistribucion (Distribucion distribucion);

    Distribucion obtenerDistribucion (Integer codigoDistribucion) throws Exception;
}
