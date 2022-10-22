package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.util.List;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class Distribucion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    private String esquema;

    @PositiveOrZero
    private Integer totalSillas;

    @Column(length = 15)
    private String filas;

    @Column(length = 15)
    private String columnas;

    @OneToMany(mappedBy = "distribucion")
    private List<Sala> salas;

    public Distribucion(String esquema, Integer totalSillas, String filas, String columnas) {
        this.esquema = esquema;
        this.totalSillas = totalSillas;
        this.filas = filas;
        this.columnas = columnas;
    }
}
