package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Compra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    private LocalDateTime fecha;

    @Enumerated(EnumType.STRING)
    private MedioPago medioPago;

    private double valorTotal;

    @ManyToOne
    private Funcion funcion;

    @ManyToOne
    private Cliente cliente;

    @OneToMany(mappedBy = "compra")
    private List<CompraConfiteria> compraConfiteriaList;

    @OneToMany(mappedBy = "compra")
    private List<Boleta> boletas;

    @OneToOne(mappedBy = "compra")
    private CuponCliente cuponCliente;

    public Compra(MedioPago medioPago, Funcion funcion, Cliente cliente, CuponCliente cuponCliente) {
        this.medioPago = medioPago;
        this.fecha = LocalDateTime.now();
        this.funcion = funcion;
        this.cliente = cliente;
        this.cuponCliente = cuponCliente;
    }
}
