package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.repositorios.CompraConfiteriaRepo;
import co.edu.uniquindio.unicine.entidades.CompraConfiteria;
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
public class CompraConfiteriaTest {

    @Autowired
    private CompraConfiteriaRepo compraConfiteriaRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void Registrar(){

        CompraConfiteria compraConfiteria = new CompraConfiteria();
        CompraConfiteria guardado = compraConfiteriaRepo.save(compraConfiteria);

        Assertions.assertEquals("",guardado.getPrecio());
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Eliminar(){

        CompraConfiteria buscado = compraConfiteriaRepo.findById(5).orElse(null);
        compraConfiteriaRepo.delete(buscado);

        Assertions.assertNull(compraConfiteriaRepo.findById(5).orElse(null));
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Actualizar(){

        CompraConfiteria guardado = compraConfiteriaRepo.findById(2).orElse(null);
        guardado.setPrecio(20000);

        CompraConfiteria nuevo = compraConfiteriaRepo.save(guardado);
        Assertions.assertEquals(20000, nuevo.getPrecio());
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Obtener(){

        Optional<CompraConfiteria> buscado = compraConfiteriaRepo.findById(3);
        Assertions.assertNotNull(buscado.orElse(null));
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Listar(){

        List<CompraConfiteria> lista = compraConfiteriaRepo.findAll();
        System.out.println(lista);
    }
}
