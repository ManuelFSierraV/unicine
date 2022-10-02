package co.edu.uniquindio.unicine.Repositorios;

import co.edu.uniquindio.unicine.entidades.Confiteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfiteriaRepo extends JpaRepository<Confiteria,Integer> {
}
