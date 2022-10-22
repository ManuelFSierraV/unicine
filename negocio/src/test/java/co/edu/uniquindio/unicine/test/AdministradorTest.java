package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import co.edu.uniquindio.unicine.repositorios.AdministradorRepo;
import co.edu.uniquindio.unicine.entidades.Administrador;
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
public class AdministradorTest {

    @Autowired
    private AdministradorRepo administradorRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void Registrar(){

        Administrador administrador = new Administrador("admin1","1111");
        Administrador guardado = administradorRepo.save(administrador);

        Assertions.assertEquals("",guardado.getEmail());
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Eliminar(){

        Administrador buscado = administradorRepo.findById(5).orElse(null);
        administradorRepo.delete(buscado);

        Assertions.assertNull(administradorRepo.findById(5).orElse(null));
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Actualizar(){

        Administrador guardado = administradorRepo.findById(2).orElse(null);
        guardado.setEmail("admin2_nuevo@email.com");

        Administrador nuevo = administradorRepo.save(guardado);
        Assertions.assertEquals("admin2_nuevo@email.com", nuevo.getEmail());
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Obtener(){

        Optional<Administrador> buscado = administradorRepo.findById(3);
        Assertions.assertNotNull(buscado.orElse(null));
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Listar(){

        List<Administrador> lista = administradorRepo.findAll();
        System.out.println(lista);
    }
}
