package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CuponCliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    private String estado;

    @OneToOne
    private Compra compra;

    @ManyToOne
    private Cupon cupon;

    @ManyToOne
    private Cliente cliente;

    public CuponCliente(String estado, Cupon cupon) {
        this.estado = estado;
        this.cupon = cupon;
    }
}
