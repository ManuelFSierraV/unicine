package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.Repositorios.ClienteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClienteTest {

    @Autowired
    private ClienteRepo clienteRepo;
}
