package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.Funcion;
import co.edu.uniquindio.unicine.repositorios.HorarioRepo;
import co.edu.uniquindio.unicine.entidades.Horario;
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
public class HorarioTest {

    @Autowired
    private HorarioRepo horarioRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void Registrar(){

        Horario horario = new Horario();
        Horario guardado = horarioRepo.save(horario);

        Assertions.assertEquals("",guardado.getDia());
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Eliminar(){

        Horario buscado = horarioRepo.findById(5).orElse(null);
        horarioRepo.delete(buscado);

        Assertions.assertNull(horarioRepo.findById(5).orElse(null));
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Actualizar(){

        Horario guardado = horarioRepo.findById(2).orElse(null);
        guardado.setDia("Lunes");

        Horario nuevo = horarioRepo.save(guardado);
        Assertions.assertEquals("Lunes", nuevo.getDia());
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Obtener(){

        Optional<Horario> buscado = horarioRepo.findById(3);
        Assertions.assertNotNull(buscado.orElse(null));
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Listar(){

        List<Horario> lista = horarioRepo.findAll();
        System.out.println(lista);
    }
}
