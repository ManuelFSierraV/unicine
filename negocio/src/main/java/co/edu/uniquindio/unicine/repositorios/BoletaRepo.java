package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.entidades.Boleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoletaRepo extends JpaRepository<Boleta,Integer> {
}
