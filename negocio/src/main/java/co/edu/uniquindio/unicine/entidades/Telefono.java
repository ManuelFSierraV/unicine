package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Telefono implements Serializable {

    @Id
    private String numero;

    @ManyToOne
    private Cliente cliente;

    public Telefono(Cliente cliente) {
        this.cliente = cliente;
    }
}
