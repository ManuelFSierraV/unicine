package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.Administrador;
import co.edu.uniquindio.unicine.repositorios.AdministradorTeatroRepo;
import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
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
public class AdministradorTeatroTest {

    @Autowired
    private AdministradorTeatroRepo administradorTeatroRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void Registrar(){

        AdministradorTeatro administradorTeatro = new AdministradorTeatro("admit1@email.com","1111");
        AdministradorTeatro guardado = administradorTeatroRepo.save(administradorTeatro);

        Assertions.assertEquals("",guardado.getEmail());
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Eliminar(){

        AdministradorTeatro buscado = administradorTeatroRepo.findById(5).orElse(null);
        administradorTeatroRepo.delete(buscado);

        Assertions.assertNull(administradorTeatroRepo.findById(5).orElse(null));
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Actualizar(){

        AdministradorTeatro guardado = administradorTeatroRepo.findById(2).orElse(null);
        guardado.setEmail("admint2_nuevo@email.com");

        AdministradorTeatro nuevo = administradorTeatroRepo.save(guardado);
        Assertions.assertEquals("admint2_nuevo@email.com", nuevo.getEmail());
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Obtener(){

        Optional<AdministradorTeatro> buscado = administradorTeatroRepo.findById(3);
        Assertions.assertNotNull(buscado.orElse(null));
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Listar(){

        List<AdministradorTeatro> lista = administradorTeatroRepo.findAll();
        System.out.println(lista);
    }
}
