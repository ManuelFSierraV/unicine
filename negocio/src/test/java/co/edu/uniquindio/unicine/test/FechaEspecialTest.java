package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.Distribucion;
import co.edu.uniquindio.unicine.repositorios.FechaEspecialRepo;
import co.edu.uniquindio.unicine.entidades.FechaEspecial;
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
public class FechaEspecialTest {

    @Autowired
    private FechaEspecialRepo fechaEspecialRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void Registrar(){

        FechaEspecial fechaEspecial = new FechaEspecial();
        FechaEspecial guardado = fechaEspecialRepo.save(fechaEspecial);

        Assertions.assertEquals("",guardado.getDescripcion());
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Eliminar(){

        FechaEspecial buscado = fechaEspecialRepo.findById(5).orElse(null);
        fechaEspecialRepo.delete(buscado);

        Assertions.assertNull(fechaEspecialRepo.findById(5).orElse(null));
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Actualizar(){

        FechaEspecial guardado = fechaEspecialRepo.findById(2).orElse(null);
        guardado.setDescripcion("Nueva Descripcion");

        FechaEspecial nuevo = fechaEspecialRepo.save(guardado);
        Assertions.assertEquals("Nueva Descripcion", nuevo.getDescripcion());
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Obtener(){

        Optional<FechaEspecial> buscado = fechaEspecialRepo.findById(3);
        Assertions.assertNotNull(buscado.orElse(null));
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Listar(){

        List<FechaEspecial> lista = fechaEspecialRepo.findAll();
        System.out.println(lista);
    }
}
