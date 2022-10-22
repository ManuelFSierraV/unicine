package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.CuponCliente;
import co.edu.uniquindio.unicine.repositorios.DistribucionRepo;
import co.edu.uniquindio.unicine.entidades.Distribucion;
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
public class DistribucionTest {

    @Autowired
    private DistribucionRepo distribucionRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void Registrar(){

        Distribucion distribucion = new Distribucion();
        Distribucion guardado = distribucionRepo.save(distribucion);

        Assertions.assertEquals("",guardado.getEsquema());
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Eliminar(){

        Distribucion buscado = distribucionRepo.findById(5).orElse(null);
        distribucionRepo.delete(buscado);

        Assertions.assertNull(distribucionRepo.findById(5).orElse(null));
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Actualizar(){

        Distribucion guardado = distribucionRepo.findById(2).orElse(null);
        guardado.setEsquema("NuevoEsquema");

        Distribucion nuevo = distribucionRepo.save(guardado);
        Assertions.assertEquals("NuevoEsquema", nuevo.getEsquema());
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Obtener(){

        Optional<Distribucion> buscado = distribucionRepo.findById(3);
        Assertions.assertNotNull(buscado.orElse(null));
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Listar(){

        List<Distribucion> lista = distribucionRepo.findAll();
        System.out.println(lista);
    }
}
