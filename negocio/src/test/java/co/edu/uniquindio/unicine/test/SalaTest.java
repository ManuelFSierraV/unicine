package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.Pelicula;
import co.edu.uniquindio.unicine.repositorios.SalaRepo;
import co.edu.uniquindio.unicine.entidades.Sala;
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
public class SalaTest {

    @Autowired
    private SalaRepo salaRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void Registrar(){

        Sala sala = new Sala();
        Sala guardado = salaRepo.save(sala);

        Assertions.assertEquals("",guardado.getNombre());
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Eliminar(){

        Sala buscado = salaRepo.findById(5).orElse(null);
        salaRepo.delete(buscado);

        Assertions.assertNull(salaRepo.findById(5).orElse(null));
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Actualizar(){

        Sala guardado = salaRepo.findById(2).orElse(null);
        guardado.setNombre("NuevaSala2");

        Sala nuevo = salaRepo.save(guardado);
        Assertions.assertEquals("NuevaSala2", nuevo.getNombre());
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Obtener(){

        Optional<Sala> buscado = salaRepo.findById(3);
        Assertions.assertNotNull(buscado.orElse(null));
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Listar(){

        List<Sala> lista = salaRepo.findAll();
        System.out.println(lista);
    }
}
