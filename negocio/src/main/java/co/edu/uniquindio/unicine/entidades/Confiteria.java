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
public class Confiteria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    private String nombreProducto;

    private String descripcion;

    private String precio;

    private String url_imagen;

    @OneToMany(mappedBy = "confiteria")
    private List<CompraConfiteria> compraConfiteriaList;
}
