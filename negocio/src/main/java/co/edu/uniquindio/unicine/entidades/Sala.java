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
public class Sala implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    private String nombre;

    @ManyToOne
    private Teatro teatro;

    @ManyToOne
    private Distribucion distribucion;

    @OneToMany(mappedBy = "sala")
    private List<Funcion> funciones;

}
