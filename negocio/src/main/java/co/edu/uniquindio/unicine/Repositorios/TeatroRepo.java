package co.edu.uniquindio.unicine.Repositorios;

import co.edu.uniquindio.unicine.entidades.Teatro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeatroRepo extends JpaRepository<Teatro,Integer> {
}
