package Proyecto.ComandApp.factory;

import Proyecto.ComandApp.repositories.MesaRepository;
import Proyecto.ComandApp.repositories.ProductoRepository;
import Proyecto.ComandApp.security.entity.Rol;
import Proyecto.ComandApp.security.entity.Usuario;
import Proyecto.ComandApp.security.enums.RolNombre;
import Proyecto.ComandApp.security.repository.UsuarioRepository;
import Proyecto.ComandApp.security.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class UserFactory {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private MesaRepository mesaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private RolService rolService;

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    PasswordEncoder passwordEncoder;


    @PostConstruct
    public void init() throws SQLException {
        crearRoles();
        crearAdmin();
        executeScript();
    }

    public void crearAdmin(){
        if (usuarioRepository.findAll().isEmpty()) {
            Set<Rol> roles = new HashSet<>();
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());

            Usuario admin = new Usuario();
            admin.setNombre("admin");
            admin.setEmail("admin@admin");
            admin.setUsuario("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRoles(roles);
            usuarioRepository.save(admin);
        }
    }

    public void executeScript() throws SQLException {
       if(mesaRepository.findAll().isEmpty() && productoRepository.findAll().isEmpty()){
           try {
               ClassPathResource scriptResource = new ClassPathResource("db/insert.sql");
               ScriptUtils.executeSqlScript(dataSource.getConnection(), scriptResource);
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
    }

    public void crearRoles() {
        Rol rolAdmin = new Rol(RolNombre.ROLE_ADMIN);
        Rol rolUser = new Rol(RolNombre.ROLE_USER);

        Optional<Rol> admin = rolService.getByRolNombre(rolAdmin.getRolNombre());
        Optional<Rol> user = rolService.getByRolNombre(rolAdmin.getRolNombre());

        if(!admin.isPresent()){
            rolService.save(rolAdmin);
        }

        if(!user.isPresent()){
            rolService.save(rolUser);
        }
    }
}
