package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.entidades.Teatro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeatroRepo extends JpaRepository<Teatro,Integer> {

    @Query("select  t from Teatro t where t.ciudad.nombre = :nombreCiudad")
    List<Teatro> listar (String nombreCiudad);

    @Query("select teatro.ciudad.codigo, teatro.ciudad.nombre, count(teatro) from Teatro teatro group by teatro.ciudad")
    List<Object[]> contarCiudad();

    Optional<Teatro> findByNombre(String nombre);

}
