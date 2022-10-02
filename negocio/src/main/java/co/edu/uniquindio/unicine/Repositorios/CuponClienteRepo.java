package co.edu.uniquindio.unicine.Repositorios;

import co.edu.uniquindio.unicine.entidades.CuponCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuponClienteRepo extends JpaRepository<CuponCliente,Integer> {
}
