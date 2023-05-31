package Proyecto.ComandApp.entities;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRODUCTOS")
public class Producto implements Serializable {

    private static final long serialVersionUID = 180802329613616000L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TIPO")     //TODO --> quizas enum mejor
    private String tipo;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "CANTIDAD")
    private int cantidad;

    @Column(name="PRECIO")
    private int precio;

    @ManyToMany(mappedBy = "productos")
    private List<Mesa> mesas;




}
