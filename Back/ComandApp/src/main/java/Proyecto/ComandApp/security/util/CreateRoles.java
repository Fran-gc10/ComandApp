package Proyecto.ComandApp.security.util;

import Proyecto.ComandApp.security.entity.Rol;
import Proyecto.ComandApp.security.enums.RolNombre;
import Proyecto.ComandApp.security.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

//@Component
//public class CreateRoles implements CommandLineRunner {
//
//    @Autowired
//    RolService rolService;
//
//    @Override
//    public void run(String... args) {
//        Rol rolAdmin = new Rol(RolNombre.ROLE_ADMIN);
//        Rol rolUser = new Rol(RolNombre.ROLE_USER);
//
//        Optional<Rol> admin = rolService.getByRolNombre(rolAdmin.getRolNombre());
//        Optional<Rol> user = rolService.getByRolNombre(rolAdmin.getRolNombre());
//
//        if(!admin.isPresent()){
//            rolService.save(rolAdmin);
//        }
//
//        if(!user.isPresent()){
//            rolService.save(rolUser);
//        }
//    }
//}
