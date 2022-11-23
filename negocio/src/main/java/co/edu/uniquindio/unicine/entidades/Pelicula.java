package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pelicula implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false, length = 100)
    private String nombre;

    @ElementCollection
    private Map<String,String> imagenes;

    private String urlTrailer;

    @Lob
    private String sinopsis;

    @Lob
    private String reparto;

    private String estado;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Genero>  genero;

    @OneToMany(mappedBy = "pelicula")
    private List<Funcion> funciones;

    public Pelicula(String nombre, String urlTrailer, String sinopsis, String reparto, String estado, List<Genero> genero) {
        this.nombre = nombre;
        this.urlTrailer = urlTrailer;
        this.sinopsis = sinopsis;
        this.reparto = reparto;
        this.estado = estado;
        this.genero = genero;
    }
}
