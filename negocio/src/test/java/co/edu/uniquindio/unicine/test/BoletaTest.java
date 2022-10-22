package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.repositorios.BoletaRepo;
import co.edu.uniquindio.unicine.entidades.Boleta;
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
public class BoletaTest {

    @Autowired
    private BoletaRepo boletaRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void Registrar(){

        Boleta boleta = new Boleta();
        Boleta guardado = boletaRepo.save(boleta);

        Assertions.assertEquals("",guardado.getPrecio());
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Eliminar(){

        Boleta buscado = boletaRepo.findById(5).orElse(null);
        boletaRepo.delete(buscado);

        Assertions.assertNull(boletaRepo.findById(5).orElse(null));
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Actualizar(){

        Boleta guardado = boletaRepo.findById(2).orElse(null);
        guardado.setPrecio(20000);

        Boleta nuevo = boletaRepo.save(guardado);
        Assertions.assertEquals(20000, nuevo.getPrecio());
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Obtener(){

        Optional<Boleta> buscado = boletaRepo.findById(3);
        Assertions.assertNotNull(buscado.orElse(null));
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Listar(){

        List<Boleta> lista = boletaRepo.findAll();
        System.out.println(lista);
    }
}
