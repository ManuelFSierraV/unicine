package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.entidades.Telefono;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefonoRepo extends JpaRepository<Telefono,String > {
}
