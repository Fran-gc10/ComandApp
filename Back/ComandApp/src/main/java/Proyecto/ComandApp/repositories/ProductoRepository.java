package Proyecto.ComandApp.repositories;

import Proyecto.ComandApp.entities.Producto;
import Proyecto.ComandApp.enums.TipoProd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {
    Optional<Producto> findByNombre(String nombre);

    List<Producto> findByTipo(TipoProd tipo);
}
