package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.CompraConfiteria;
import co.edu.uniquindio.unicine.repositorios.CuponClienteRepo;
import co.edu.uniquindio.unicine.entidades.CuponCliente;
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
public class CuponClienteTest {

    @Autowired
    private CuponClienteRepo cuponClienteRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void Registrar(){

        CuponCliente cuponCliente = new CuponCliente();
        CuponCliente guardado = cuponClienteRepo.save(cuponCliente);

        Assertions.assertEquals("",guardado.getEstado());
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Eliminar(){

        CuponCliente buscado = cuponClienteRepo.findById(5).orElse(null);
        cuponClienteRepo.delete(buscado);

        Assertions.assertNull(cuponClienteRepo.findById(5).orElse(null));
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Actualizar(){

        CuponCliente guardado = cuponClienteRepo.findById(2).orElse(null);
        guardado.setEstado("Invalido");

        CuponCliente nuevo = cuponClienteRepo.save(guardado);
        Assertions.assertEquals("Invalido", nuevo.getEstado());
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Obtener(){

        Optional<CuponCliente> buscado = cuponClienteRepo.findById(3);
        Assertions.assertNotNull(buscado.orElse(null));
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Listar(){

        List<CuponCliente> lista = cuponClienteRepo.findAll();
        System.out.println(lista);
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerCuponesTodosClientes(){
        List<Object[]> cupones = cuponClienteRepo.obtenerCuponesTodosClientes();
        cupones.forEach( o ->
                System.out.println(o [0] + "," + o[1] + "," + o[2])
        );
    }

}
