package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.dto.FuncionDto;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import co.edu.uniquindio.unicine.repositorios.FuncionRepo;
import co.edu.uniquindio.unicine.entidades.Funcion;
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
public class FuncionTest {

    @Autowired
    private FuncionRepo funcionRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void Registrar(){

        Funcion funcion = new Funcion();
        Funcion guardado = funcionRepo.save(funcion);

        Assertions.assertEquals("",guardado.getPrecio());
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Eliminar(){

        Funcion buscado = funcionRepo.findById(5).orElse(null);
        funcionRepo.delete(buscado);

        Assertions.assertNull(funcionRepo.findById(5).orElse(null));
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Actualizar(){

        Funcion guardado = funcionRepo.findById(2).orElse(null);
        guardado.setPrecio(20000);

        Funcion nuevo = funcionRepo.save(guardado);
        Assertions.assertEquals(20000, nuevo.getPrecio());
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Obtener(){

        Optional<Funcion> buscado = funcionRepo.findById(3);
        Assertions.assertNotNull(buscado.orElse(null));
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Listar(){

        List<Funcion> lista = funcionRepo.findAll();
        System.out.println(lista);
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerPeliculaFuncion(){
        String nombrePelicula = funcionRepo.obtenerNombrePelicula(5);
        Assertions.assertEquals("harry", nombrePelicula);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerPeliculas(){
        List<Pelicula> listaPelicula = funcionRepo.obtenerPelicula();
        listaPelicula.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerFunciones(){
        List<Funcion> listaFunciones = funcionRepo.listaFunciones(1);
        listaFunciones.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerFuncionesSinCompra(){
        List<Funcion> listaFuncion = funcionRepo.funcionesSinCompra(1);
        System.out.println(listaFuncion);
    }

}
