package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.dto.InfoCompraDto;
import co.edu.uniquindio.unicine.entidades.Boleta;
import co.edu.uniquindio.unicine.entidades.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraRepo extends JpaRepository<Compra,Integer> {

    @Query("select co from Cliente cli, in(cli.compras) co where cli.email = :email")
    List<Compra> obtenerCompras (String email);

    @Query("select co from Cliente cli join cli.compras co where cli.email = :email")
    List<Compra> obtenerComprasOpcion2 (String email);

    @Query("select e from Compra c join c.boletas e where c.codigo = :codigoCompra")
    List<Boleta> obtenerBoletas (Integer codigoCompra);

    @Query("select c.cliente.cedula, count (c)from Compra c where c.cuponCliente is not null  group by c.cliente")
    List<Object[]> cuponesRedimidos();

    @Query("select sum(c.valorTotal) from Compra c where c.cliente.cedula = :cedulaCliente")
    float calcularValorGastado(String cedulaCliente);

    @Query("select c,c.cliente from Compra c order by c.valorTotal desc ")
    List<Object[]> compraMasCostosa();

    @Query("select c1,c1.cliente from Compra c1 where c1.valorTotal=(select max (c.valorTotal) from Compra c) ")
    List<Object[]> compraMasCostosa2();

   // @Query("select new co.edu.uniquindio.unicine.dto.InfoCompraDto(c.valorTotal,c.fecha,c.funcion," +
   //         "(select sum (b.precio) from Boleta b where b.compra.codigo = c.codigo)," +
   //         "(select sum(cc.precio*cc.unidades) from CompraConfiteria cc where cc.compra.codigo = c.codigo))" +
   //         "from Compra c where c.cliente.cedula = :cedulaCliente")
   // List<InfoCompraDto>informacionCompra(String cedulaCliente);


    @Query("select c.funcion.pelicula, count (c)from Compra c where c.funcion.sala.teatro.ciudad.codigo = :codigoCiudad group by c.funcion.pelicula")
    List<Object[]> peliculaMasVista (Integer codigoCiudad);
}
