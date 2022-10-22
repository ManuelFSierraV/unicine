package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.dto.FuncionDto;
import co.edu.uniquindio.unicine.entidades.Funcion;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface FuncionRepo extends JpaRepository<Funcion,Integer> {

    @Query("select f.pelicula.nombre from Funcion f where f.codigo = :codigoFuncion")
    String obtenerNombrePelicula(Integer codigoFuncion);

    @Query("select distinct f.pelicula from Funcion  f")
    List<Pelicula> obtenerPelicula();

    @Query("select f.pelicula.nombre, f.pelicula.estado, f.pelicula.urlImagen, f.sala.codigo, f.sala.teatro.direccion, " +
            "f.sala.teatro.ciudad.nombre, f.horario from Funcion f where f.pelicula.codigo = :codigoPelicula")
    List<FuncionDto> listaFunciones (Integer codigoPelicula);

    @Query("select f from Funcion f where f.sala.teatro.codigo = :codigoTeatro and f.compras is empty ")
    List<Funcion> funcionesSinCompra (Integer codigoTeatro);

    @Query("select f from Funcion f where f.sala.teatro.codigo = :codigoTeatro and f.horario.fechaInicio < :fechaInicio or f.horario.fechaFin > :fechaInicio")
    List<Funcion> funcionesTeatro (Integer codigoteatro, LocalDate fechaInicio, LocalDate fechaFin);

    @Query("select funcion.sala from Funcion funcion where funcion.sala.codigo = :codigoSala")
    Optional<Funcion> buscarSalaPorHorario(Integer codigoSala);

}
