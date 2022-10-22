package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.Teatro;
import co.edu.uniquindio.unicine.repositorios.TelefonoRepo;
import co.edu.uniquindio.unicine.entidades.Telefono;
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
public class TelefonoTest {

    @Autowired
    private TelefonoRepo telefonoRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void Registrar(){

        Telefono telefono = new Telefono();
        Telefono guardado = telefonoRepo.save(telefono);

        Assertions.assertEquals("",guardado.getNumero());
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Eliminar(){

        Telefono buscado = telefonoRepo.findById("3130987654").orElse(null);
        telefonoRepo.delete(buscado);

        Assertions.assertNull(telefonoRepo.findById("3130987654").orElse(null));
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Actualizar(){

        Telefono guardado = telefonoRepo.findById("3130987654").orElse(null);
        guardado.setNumero("3130000000");

        Telefono nuevo = telefonoRepo.save(guardado);
        Assertions.assertEquals("3130000000", nuevo.getNumero());
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Obtener(){

        Optional<Telefono> buscado = telefonoRepo.findById("3130987654");
        Assertions.assertNotNull(buscado.orElse(null));
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Listar(){

        List<Telefono> lista = telefonoRepo.findAll();
        System.out.println(lista);
    }
}
