package Proyecto.ComandApp.json;

import Proyecto.ComandApp.entities.Mesa;
import Proyecto.ComandApp.security.entity.Rol;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioRest implements Serializable {

    private static final long serialVersionUID = 2562292635410148858L;
    private Long idUsuario;
    private String nombre;
    private String nombreUsuario;
    private String email;
    private String password;
    private Set<Rol> roles = new HashSet<>();
    private List<Mesa> mesas;

}
