package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.repositorios.CiudadRepo;
import co.edu.uniquindio.unicine.entidades.Ciudad;
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
public class CiudadTest {

    @Autowired
    private CiudadRepo ciudadRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void Registrar(){

        Ciudad ciudad = new Ciudad("Neiva");
        Ciudad guardado = ciudadRepo.save(ciudad);

        Assertions.assertEquals("",guardado.getNombre());
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Eliminar(){

        Ciudad buscado = ciudadRepo.findById(5).orElse(null);
        ciudadRepo.delete(buscado);

        Assertions.assertNull(ciudadRepo.findById(5).orElse(null));
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Actualizar(){

        Ciudad guardado = ciudadRepo.findById(2).orElse(null);
        guardado.setNombre("Neiva");

        Ciudad nuevo = ciudadRepo.save(guardado);
        Assertions.assertEquals("Neiva", nuevo.getNombre());
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Obtener(){

        Optional<Ciudad> buscado = ciudadRepo.findById(3);
        Assertions.assertNotNull(buscado.orElse(null));
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Listar(){

        List<Ciudad> lista = ciudadRepo.findAll();
        System.out.println(lista);
    }
}
