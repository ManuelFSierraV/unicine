package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.Sala;
import co.edu.uniquindio.unicine.repositorios.SolicitudRepo;
import co.edu.uniquindio.unicine.entidades.Solicitud;
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
public class SolicitudTest {

    @Autowired
    private SolicitudRepo solicitudRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void Registrar(){

        Solicitud solicitud = new Solicitud();
        Solicitud guardado = solicitudRepo.save(solicitud);

        Assertions.assertEquals("",guardado.getTipo());
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Eliminar(){

        Solicitud buscado = solicitudRepo.findById(5).orElse(null);
        solicitudRepo.delete(buscado);

        Assertions.assertNull(solicitudRepo.findById(5).orElse(null));
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Actualizar(){

        Solicitud guardado = solicitudRepo.findById(2).orElse(null);
        guardado.setTipo("NuevoTipo");

        Solicitud nuevo = solicitudRepo.save(guardado);
        Assertions.assertEquals("NuevoTipo", nuevo.getTipo());
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Obtener(){

        Optional<Solicitud> buscado = solicitudRepo.findById(3);
        Assertions.assertNotNull(buscado.orElse(null));
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Listar(){

        List<Solicitud> lista = solicitudRepo.findAll();
        System.out.println(lista);
    }
}
