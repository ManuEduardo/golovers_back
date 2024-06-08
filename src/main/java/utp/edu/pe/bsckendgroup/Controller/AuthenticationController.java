package utp.edu.pe.bsckendgroup.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.bsckendgroup.Domain.Student.DataRegisterStudent;
import utp.edu.pe.bsckendgroup.Domain.Student.Student;
import utp.edu.pe.bsckendgroup.Infra.Jwt.DataLoginStudent;
import utp.edu.pe.bsckendgroup.Infra.Jwt.DatosJWTToken;
import utp.edu.pe.bsckendgroup.Infra.Jwt.TokenService;
import utp.edu.pe.bsckendgroup.Service.StudentService;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<?> autenticarUsuario(@RequestBody @Valid DataLoginStudent datosLogin){
        try {
            Authentication authToken = new UsernamePasswordAuthenticationToken(datosLogin.email(), datosLogin.password());
            var usuarioAutenticado = authenticationManager.authenticate(authToken);
            var JWTtoken = tokenService.generarToken((Student) usuarioAutenticado.getPrincipal());
            return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerStudent(DataRegisterStudent data) {
        return studentService.save(data);
    }
}
