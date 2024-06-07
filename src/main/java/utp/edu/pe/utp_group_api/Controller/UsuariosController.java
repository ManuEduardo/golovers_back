package utp.edu.pe.utp_group_api.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.utp_group_api.Domain.Role.Role;
import utp.edu.pe.utp_group_api.Domain.Role.RoleRepository;
import utp.edu.pe.utp_group_api.Domain.User.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuariosController {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/registerAdmin")
    public ResponseEntity<?> registrarAdmin(@RequestBody @Valid DatosRegistroUsers datos) {
        if (usersRepository.existsByUsername(datos.username())) {
            new ResponseEntity<>("Usuario ya existe", HttpStatus.BAD_REQUEST);
        }
        Usuario usuario = new Usuario();
        usuario.setName(datos.name());
        usuario.setUsername(datos.username());
        usuario.setPassword(passwordEncoder.encode(datos.password()));
        Role role = roleRepository.findByName("ADMIN").get();
        usuario.setRoles(Collections.singletonList(role));
        usersRepository.save(usuario);
        return new ResponseEntity<>("Admin registrado", HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<?> obtenerUsuarios() {
        List<Usuario> usuarios = usersRepository.findAll();
        List<DatosListadoUsers> response = usuarios.stream()
                .map(DatosListadoUsers::new).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> obtenerUsuarioPorId(@PathVariable Long id) {
        Usuario usuario = usersRepository.findById(id).orElse(null);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new DatosListadoUsers(usuario));
    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizarUsuario( @RequestBody @Valid DatosActualizarUser datos) {
        Usuario usuario = usersRepository.findById(datos.id()).orElse(null);
        if (usuario == null) {
            return new ResponseEntity<> ("Usuario no encontrado", HttpStatus.NOT_FOUND);
        }
        usuario = new Usuario(datos);
        usersRepository.save(usuario);
        return ResponseEntity.ok(new DatosListadoUsers(usuario));
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
        Usuario usuario = usersRepository.findById(id).orElse(null);
        if (usuario == null) {
            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
        }
        usersRepository.delete(usuario);
        return ResponseEntity.ok("Usuario eliminado");
    }

}
