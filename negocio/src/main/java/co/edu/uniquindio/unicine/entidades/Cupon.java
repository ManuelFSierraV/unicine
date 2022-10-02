package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cupon implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    private String descripcion;

    private String descuento;

    private String estado;

    private LocalDateTime fechaVencimiento;

    @OneToMany(mappedBy = "cupon")
    private List<CuponCliente> cuponClientes;
}
