package co.edu.uniquindio.unicine.Repositorios;

import co.edu.uniquindio.unicine.entidades.FechaEspecial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FechaEspecialRepo extends JpaRepository<FechaEspecial,Integer> {
}
