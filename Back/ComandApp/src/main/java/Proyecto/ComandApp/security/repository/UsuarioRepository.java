package Proyecto.ComandApp.security.repository;

import Proyecto.ComandApp.security.entity.Usuario;
import Proyecto.ComandApp.security.enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByNombreUsuario(String nombreUsuario);

    Optional<Usuario> findByRoles_RolNombre(RolNombre rolNombre);

    boolean existsByNombreUsuario (String nombreUsuario);
    boolean existsByEmail (String email);
}
