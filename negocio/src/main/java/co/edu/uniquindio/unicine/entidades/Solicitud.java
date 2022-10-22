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
public class Solicitud implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false)
    private String tipo;

    private String descripcion;

    @ManyToOne
    private Cliente cliente;

    public Solicitud(String tipo, String descripcion, Cliente cliente) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.cliente = cliente;
    }
}
