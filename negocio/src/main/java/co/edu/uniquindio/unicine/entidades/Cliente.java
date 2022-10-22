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
    private List<Compra> compras;

    @OneToMany(mappedBy = "cliente")
    private List<CuponCliente> cuponesCliente;

    @OneToMany(mappedBy = "cliente")
    private List<Solicitud> solicitudes;

    @OneToMany(mappedBy = "cliente")
    private List<Telefono> telefonos;

    @OneToMany(mappedBy = "cliente")
    private List<FechaEspecial> fechaEspeciales;

    public Cliente(String cedula, String nombre, String email, String url_foto, String password) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.email = email;
        this.url_foto = url_foto;
        this.password = password;
        this.estado = false;
    }
}
