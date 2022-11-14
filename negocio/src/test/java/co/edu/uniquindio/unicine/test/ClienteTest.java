package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repositorios.ClienteRepo;
import co.edu.uniquindio.unicine.servicios.EmailServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClienteTest {

    @Autowired
    private ClienteRepo clienteRepo;
    private EmailServicio emailServicio;

    @Test
    @Sql("classpath:dataset.sql")
    public void Registrar(){

        Cliente cliente = new Cliente("1234","juan","juan@email.com","rutaFoto","1234");
        Cliente guardado = clienteRepo.save(cliente);

        Assertions.assertEquals("",guardado.getEmail());
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

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerClienteEmail(){
        Cliente cliente = clienteRepo.findByEmail("laura@email.com").orElse(null);
        Assertions.assertNotNull(cliente);
        System.out.println(cliente);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void paginador(){
        List<Cliente> listaClientes = clienteRepo.findAll(PageRequest.of(0,2)).toList();
        listaClientes.forEach (System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerPorEstado(){
        List<Cliente> listaClientes = clienteRepo.obtenerPorEstado( PageRequest.of(0,3), true );
        listaClientes.forEach (System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void ordenarPorNombre(){
        List<Cliente> listaClientes = clienteRepo.findAll(Sort.by("nombre"));
        listaClientes.forEach (System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void ordenarPorNombreDes(){
        List<Cliente> listaClientes = clienteRepo.findAll(Sort.by("nombre").descending());
        listaClientes.forEach (System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerCompras(){
        List<Compra> listaCompras = clienteRepo.obtenerCompra("laura@email.com");
        Assertions.assertEquals(3, listaCompras.size());
        listaCompras.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerComprasOpcion2(){
        List<Compra> listaCompras = clienteRepo.obtenerCompraOpcion3("laura@email.com");
        Assertions.assertEquals(1, listaCompras.size());
        listaCompras.forEach(System.out::println);
    }


    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerCupones(){
        List<Cupon> listaCupones = clienteRepo.obtenerCupones("laura@email.com");
        Assertions.assertEquals(2, listaCupones.size());
        listaCupones.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerComprasTodo(){
        List<Object[]> listaCompras = clienteRepo.obtenerCompraTodos();
        listaCompras.forEach( o ->
                System.out.println(o [0] + "," + o[1] + "," + o[2])
        );
    }

    /**/
    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerSolicitudes(){
        List<Solicitud> solicitudes = clienteRepo.obtenerSolicitud("222");
        solicitudes.forEach(System.out::println);
    }


    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerComprasCliente(){
        List<Compra> compras= clienteRepo.obtenerComprasCliente("111");
        compras.forEach(System.out::println);
    }

    @Test
    public void enviarEmailTest(){
        emailServicio.enviarEmail("Prueba de envio","Esto es un mensaje de prueba","karold.castanor@uqvirtual.edu.co");
    }
}
