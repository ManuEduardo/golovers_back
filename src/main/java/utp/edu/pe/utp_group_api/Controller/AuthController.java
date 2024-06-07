package utp.edu.pe.utp_group_api.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utp.edu.pe.utp_group_api.Domain.Role.Role;
import utp.edu.pe.utp_group_api.Domain.Role.RoleRepository;
import utp.edu.pe.utp_group_api.Domain.User.DatosRegistroUsers;
import utp.edu.pe.utp_group_api.Domain.User.UsersRepository;
import utp.edu.pe.utp_group_api.Domain.User.Usuario;
import utp.edu.pe.utp_group_api.Infra.Security.TokenService;
import utp.edu.pe.utp_group_api.Infra.Security.JwtResponse;
import utp.edu.pe.utp_group_api.Infra.Security.SessionRequest;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final TokenService jwtGenerator;
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;
    private UsersRepository usersRepository;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, RoleRepository roleRepository, UsersRepository usersRepository, TokenService jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.usersRepository = usersRepository;
        this.jwtGenerator = jwtGenerator;
    }
    @PostMapping("/register")
    public ResponseEntity<?> registrar(@RequestBody @Valid DatosRegistroUsers datos) {
        if (usersRepository.existsByUsername(datos.username())) {
            new ResponseEntity<>("Usuario ya existe", HttpStatus.BAD_REQUEST);
        }
        Usuario usuario = new Usuario();
        usuario.setName(datos.name());
        usuario.setUsername(datos.username());
        usuario.setPassword(passwordEncoder.encode(datos.password()));
        Role role = roleRepository.findByName("STUDENT").get();
        usuario.setRoles(Collections.singletonList(role));
        usersRepository.save(usuario);
        return new ResponseEntity<>("Usuario registrado", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody @Valid SessionRequest datos) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(datos.username(), datos.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtGenerator.generarToken(authentication);
        return new ResponseEntity<>(new JwtResponse(jwt), HttpStatus.OK);
    }

}
