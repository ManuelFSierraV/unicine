package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Compra;
import co.edu.uniquindio.unicine.entidades.Cupon;
import co.edu.uniquindio.unicine.entidades.Solicitud;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepo extends JpaRepository<Cliente,String> {

    @Query("Select c from Cliente c where c.email = :email")
    Cliente buscarCliente(String email);

    Optional<Cliente> findByEmail(String email);

    @Query("select c from Cliente c where c.email = :email and c.password = :password")
    Cliente comprobarAutenticacion (String email, String password);

    @Query("select c from Cliente c where c.estado = :estado")
    List<Cliente> obtenerPorEstado (Pageable paginador,Boolean estado);

    @Query("select cup from Cliente cli join cli.cuponesCliente cup where cli.email = :email")
    List<Cupon> obtenerCupones (String email);

    @Query("select comp from Cliente cliente, in(cliente.compras) comp where cliente.email = :email")
    List<Compra> obtenerCompra (String email);

    @Query("select c from Compra c where c.cliente.cedula = :cedula")
    List<Compra> obtenerComprasCliente (String cedula);

    @Query("select comp from Cliente cliente join cliente.compras comp where cliente.email = :email")
    List<Compra> obtenerCompraOpcion3(String email);

    @Query("select cliente.nombre, cliente.email, comp from Cliente cliente left join cliente.compras comp")
    List<Object[]> obtenerCompraTodos();

    @Query("select s from Solicitud s where s.cliente.cedula = :cedula ")
    List<Solicitud> obtenerSolicitud(String cedula);

}
