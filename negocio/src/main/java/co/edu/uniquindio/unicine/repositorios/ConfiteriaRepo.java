package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.entidades.Confiteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfiteriaRepo extends JpaRepository<Confiteria,Integer> {

    Optional<Confiteria> findByNombre(String nombreConfiteria);

}
