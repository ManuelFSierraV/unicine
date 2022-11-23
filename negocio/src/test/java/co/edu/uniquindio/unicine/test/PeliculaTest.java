package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.dto.HorarioSalaDto;
import co.edu.uniquindio.unicine.entidades.Genero;
import co.edu.uniquindio.unicine.repositorios.PeliculaRepo;
import co.edu.uniquindio.unicine.entidades.Pelicula;
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
public class PeliculaTest {

    @Autowired
    private PeliculaRepo peliculaRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void Registrar(){

        Pelicula pelicula = new Pelicula();
        Pelicula guardado = peliculaRepo.save(pelicula);

        Assertions.assertEquals("",guardado.getEstado());
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Eliminar(){

        Pelicula buscado = peliculaRepo.findById(5).orElse(null);
        peliculaRepo.delete(buscado);

        Assertions.assertNull(peliculaRepo.findById(5).orElse(null));
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Actualizar(){

        Pelicula guardado = peliculaRepo.findById(2).orElse(null);
        guardado.setUrlImagen("NuevaImagen");

        Pelicula nuevo = peliculaRepo.save(guardado);
        Assertions.assertEquals("NuevaImagen", nuevo.getUrlImagen());
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Obtener(){

        Optional<Pelicula> buscado = peliculaRepo.findById(3);
        Assertions.assertNotNull(buscado.orElse(null));
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void Listar(){

        List<Pelicula> lista = peliculaRepo.findAll();
        System.out.println(lista);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void buscarPelicula(){
        List<Pelicula> peliculas = peliculaRepo.buscarPelicula("rapidos","cartelera");
        peliculas.forEach(System.out::println);
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerPeliculasPorEstado() {
        List<Pelicula> peliculas = peliculaRepo.obtenerPeliculasPorEstado(1,"Cartelera");
        peliculas.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerPeliculasPorGenero() {
        List<Pelicula> peliculas = peliculaRepo.obtenerPeliculasPorGenero(Genero.TERROR);
        peliculas.forEach(System.out::println);
    }


    @Test
    @Sql("classpath:dataset.sql")
    public void listasHorario(){
        List<HorarioSalaDto> listaHoraraio = peliculaRepo.listarHorarios(1, 1);
        System.out.println(listaHoraraio);
    }
}
