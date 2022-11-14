package co.edu.uniquindio.unicine.dto;

import co.edu.uniquindio.unicine.entidades.Funcion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class InfoCompraDto {

    private float precioTotal;
    private LocalDateTime fechaCompra;
    private Funcion funcion;
    private double precioBoletas;
    private double precioConfiteria;
}
