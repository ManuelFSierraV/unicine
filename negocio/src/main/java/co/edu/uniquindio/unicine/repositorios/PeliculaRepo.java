package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.dto.HorarioSalaDto;
import co.edu.uniquindio.unicine.dto.PeliculaFuncion;
import co.edu.uniquindio.unicine.entidades.Genero;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeliculaRepo extends JpaRepository<Pelicula,Integer> {



    Optional<Pelicula> findByNombre(String nombrePelicula);

    @Query("select p from Pelicula p where p.nombre like concat('%',:nombre,'%') and p.estado = :estado ")
    List<Pelicula> buscarPelicula(String nombre,String estado);

    @Query("select pelicula from Pelicula pelicula where pelicula.nombre = :nombrePelicula")
    Pelicula buscarPeliculaPorNombre(String nombrePelicula);

    @Query("select new co.edu.uniquindio.unicine.dto.PeliculaFuncion(p,f) from Pelicula p left join p.funciones f where p.nombre like concat('%',:nombre,'%') ")
    List<PeliculaFuncion> buscarFuncionPelicula(String nombre);

    @Query("select distinct f.pelicula from Funcion f where f.sala.teatro.ciudad.codigo = :codigo and f.pelicula.estado = :estado")
    List<Pelicula> obtenerPeliculasPorEstadoCiudad(Integer codigo, String estado);

    @Query("select distinct f.pelicula from Funcion f where f.sala.teatro.ciudad.codigo = :codigo and f.pelicula.estado = :estado")
    List<Pelicula> obtenerPeliculasPorEstado(String estado);

    @Query("select p from Pelicula p where p.genero = :genero")
    List<Pelicula> obtenerPeliculasPorGenero(Genero genero);

    @Query("select pelicula from Pelicula pelicula where pelicula.nombre like concat('%', :nombre, '%') and pelicula.estado = :estado")
    Optional<Pelicula> buscarPeliculaEstado(String nombre, Boolean estado);

    @Query("select new co.edu.uniquindio.unicine.dto.HorarioSalaDto(f.horario,f.sala) from Pelicula p join p.funciones f where p.codigo = :codigoPelicula and " +
            "f.sala.teatro.codigo = :codigoTeatro")
    List<HorarioSalaDto> listarHorarios (Integer codigoPelicula, Integer codigoTeatro);

    @Query("select p from Pelicula p where :generoPelicula member of p.genero order by p.nombre asc")
    List<Pelicula> listarPelicula(Genero generoPelicula);
}
