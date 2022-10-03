package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
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
    @Column(length = 20)
    private String cedula;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Email
    @Column(unique = true, length = 150)
    private String email;

    private String url_foto;

    @Column(nullable = false, length = 50)
    private String password;

    private Boolean estado;

    @OneToMany(mappedBy = "cliente")
    private List<Compra> compraList;

    @OneToMany(mappedBy = "cliente")
    private List<CuponCliente> cuponClienteList;

    @OneToMany(mappedBy = "cliente")
    private List<Solicitud> solicitudes;

    @OneToMany(mappedBy = "cliente")
    private List<Telefono> telefonos;
}
