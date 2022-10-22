package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.Boleta;
import co.edu.uniquindio.unicine.entidades.CompraConfiteria;
import co.edu.uniquindio.unicine.repositorios.CompraRepo;
import co.edu.uniquindio.unicine.entidades.Compra;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CompraTest {

    @Autowired
    private CompraRepo compraRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void Registrar(){

        Compra compra = new Compra();
        Compra guardado = compraRepo.save(compra);

        Assertions.assertEquals("",guardado.getValorTotal());
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Eliminar(){

        Compra buscado = compraRepo.findById(5).orElse(null);
        compraRepo.delete(buscado);

        Assertions.assertNull(compraRepo.findById(5).orElse(null));
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Actualizar(){

        Compra guardado = compraRepo.findById(2).orElse(null);
        guardado.setValorTotal(20000);

        Compra nuevo = compraRepo.save(guardado);
        Assertions.assertEquals(20000, nuevo.getValorTotal());
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Obtener(){

        Optional<Compra> buscado = compraRepo.findById(3);
        Assertions.assertNotNull(buscado.orElse(null));
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Listar(){

        List<Compra> lista = compraRepo.findAll();
        System.out.println(lista);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerBoletaCompras() {
        List<Boleta> listaBoletas = compraRepo.obtenerBoletas(1);
        Assertions.assertEquals(2, listaBoletas.size());
        listaBoletas.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void contarCuponesRedimidos() {
        List<Object[]> listaCupones = compraRepo.cuponesRedimidos();
        listaCupones.forEach(o ->
                System.out.println(o[0] + "," + o[1])
        );
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void calcularValorTotalGastados() {
        float valorTotal = compraRepo.calcularValorGastado("111");
        //Assertions.assertEquals();
        System.out.println(valorTotal);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerCompraMaxCostosa() {
        List<Object[]> comprasCostosas = compraRepo.compraMasCostosa();
        comprasCostosas.forEach(o ->
                System.out.println(o[0] + "," + o[1])
        );
    }


    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerPeliculaMasVista() {
        List<Object[]> peliculas = compraRepo.peliculaMasVista(1);
        peliculas.forEach(o ->
                System.out.println(o[0] + "," + o[1])
        );
    }

}
