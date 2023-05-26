package Proyecto.ComandApp.security.repository;

import Proyecto.ComandApp.security.entity.Rol;
import Proyecto.ComandApp.security.enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {

    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
