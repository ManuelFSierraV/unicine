package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.entidades.Distribucion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistribucionRepo extends JpaRepository<Distribucion,Integer> {
}
