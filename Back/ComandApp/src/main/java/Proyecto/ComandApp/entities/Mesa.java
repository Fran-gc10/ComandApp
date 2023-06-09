package Proyecto.ComandApp.entities;

import Proyecto.ComandApp.security.entity.Usuario;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="MESAS")
public class Mesa implements Serializable {

    private static final long serialVersionUID = 2455208515714808003L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="NUMERO")
    private String numero;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "PRODUCTOS_MESA",
            joinColumns = @JoinColumn(name = "MESA_ID", nullable=false),
            inverseJoinColumns = @JoinColumn(name = "PRODUCTO_ID", nullable=false))
    private List<Producto> productos;

}
