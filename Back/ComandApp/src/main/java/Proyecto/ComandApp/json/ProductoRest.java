package Proyecto.ComandApp.json;

import Proyecto.ComandApp.enums.TipoProd;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductoRest implements Serializable {

    private static final long serialVersionUID = -2603219680568965288L;
    private Long id;
    private TipoProd tipo;
    private String nombre;
    private int cantidad;
    private float precio;
    private List<MesaRest> mesas;

}
