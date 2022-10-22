package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FechaEspecial implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    private LocalDateTime fechaReserva;

    private String descripcion;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Sala sala;

    public FechaEspecial(LocalDateTime fechaReserva, String descripcion, Cliente cliente, Sala sala) {
        this.fechaReserva = fechaReserva;
        this.descripcion = descripcion;
        this.cliente = cliente;
        this.sala = sala;
    }
}
