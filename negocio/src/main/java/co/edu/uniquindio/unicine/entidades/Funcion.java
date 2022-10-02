package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Funcion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    private float precio;

    @ManyToOne
    private Sala sala;

    @ManyToOne
    private Horario horario;

    @ManyToOne
    private Pelicula pelicula;

    @OneToMany(mappedBy = "funcion")
    private List<Compra> compras;
}
