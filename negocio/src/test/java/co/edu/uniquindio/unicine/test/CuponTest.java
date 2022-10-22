package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.CuponCliente;
import co.edu.uniquindio.unicine.repositorios.CuponRepo;
import co.edu.uniquindio.unicine.entidades.Cupon;
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
public class CuponTest {

    @Autowired
    private CuponRepo cuponRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void Registrar(){

        Cupon cupon = new Cupon();
        Cupon guardado = cuponRepo.save(cupon);

        Assertions.assertEquals("",guardado.getEstado());
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Eliminar(){

        Cupon buscado = cuponRepo.findById(5).orElse(null);
        cuponRepo.delete(buscado);

        Assertions.assertNull(cuponRepo.findById(5).orElse(null));
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Actualizar(){

        Cupon guardado = cuponRepo.findById(2).orElse(null);
        guardado.setEstado("Valido");

        Cupon nuevo = cuponRepo.save(guardado);
        Assertions.assertEquals("Valido", nuevo.getEstado());
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Obtener(){

        Optional<Cupon> buscado = cuponRepo.findById(3);
        Assertions.assertNotNull(buscado.orElse(null));
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Listar(){

        List<Cupon> lista = cuponRepo.findAll();
        System.out.println(lista);
    }
}
