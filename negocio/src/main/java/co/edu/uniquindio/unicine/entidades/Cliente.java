package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String cedula;

    private String nombre;

    private String email;

    private String url_foto;

    private String password;

    private String estado;

    @OneToMany(mappedBy = "cliente")
    private List<Compra> compraList;

    @OneToMany(mappedBy = "cliente")
    private List<CuponCliente> cuponClienteList;

    @OneToMany(mappedBy = "cliente")
    private List<Solicitud> solicitudes;

    @OneToMany(mappedBy = "cliente")
    private List<Telefono> telefonos;
}
