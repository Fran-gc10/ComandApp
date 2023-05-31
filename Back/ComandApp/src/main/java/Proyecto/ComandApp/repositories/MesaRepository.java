package Proyecto.ComandApp.repositories;

import Proyecto.ComandApp.entities.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MesaRepository extends JpaRepository<Mesa,Long> {
}
