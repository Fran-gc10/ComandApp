package Proyecto.ComandApp.json;

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
public class MesaRest implements Serializable {

    private static final long serialVersionUID = 180802329613616000L;

    private Long id;
    private String numero;
    private List<ProductoRest> productos;
}
