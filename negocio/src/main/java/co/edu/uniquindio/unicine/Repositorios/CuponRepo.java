package co.edu.uniquindio.unicine.Repositorios;

import co.edu.uniquindio.unicine.entidades.Cupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuponRepo extends JpaRepository<Cupon,Integer> {
}
