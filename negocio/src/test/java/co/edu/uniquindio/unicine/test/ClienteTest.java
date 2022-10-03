package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.Repositorios.ClienteRepo;
import co.edu.uniquindio.unicine.entidades.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClienteTest {

    @Autowired
    private ClienteRepo clienteRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void Registrar(){

    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Eliminar(){

        Cliente buscado = clienteRepo.findById("55555").orElse(null);
        clienteRepo.delete(buscado);

        Assertions.assertNull(clienteRepo.findById("55555").orElse(null));
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Actualizar(){

        Cliente guardado = clienteRepo.findById("22222").orElse(null);
        guardado.setEmail("jose_nuevo@email.com");

        Cliente nuevo = clienteRepo.save(guardado);
        Assertions.assertEquals("jose_nuevo@email.com", nuevo.getEmail());
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Obtener(){

        Optional<Cliente> buscado = clienteRepo.findById("33333");
        Assertions.assertNotNull(buscado.orElse(null));
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Listar(){

        List<Cliente> lista = clienteRepo.findAll();
        System.out.println(lista);
    }
}
