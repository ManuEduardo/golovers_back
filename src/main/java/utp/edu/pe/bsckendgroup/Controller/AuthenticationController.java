package utp.edu.pe.bsckendgroup.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.bsckendgroup.Domain.Student.DataListStudents;
import utp.edu.pe.bsckendgroup.Domain.Student.DataRegisterStudent;
import utp.edu.pe.bsckendgroup.Domain.Student.Student;
import utp.edu.pe.bsckendgroup.Domain.Student.StudentRepository;
import utp.edu.pe.bsckendgroup.Infra.Jwt.DataLoginStudent;
import utp.edu.pe.bsckendgroup.Infra.Jwt.DataJWTToken;
import utp.edu.pe.bsckendgroup.Infra.Jwt.TokenService;
import utp.edu.pe.bsckendgroup.Service.GroupsService;
import utp.edu.pe.bsckendgroup.Service.StudentService;
import utp.edu.pe.bsckendgroup.ServicesDto.DataresponseLogin;

@RestController
@RequestMapping("/login")
@Tag(name = "Authentication", description = "Autenticación de usuario")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private GroupsService groupsService;

    @Operation(summary = "Autenticar usuario", description = "Autenticar usuario")
    @PostMapping
    public ResponseEntity<?> autenticarUsuario(@RequestBody @Valid DataLoginStudent datosLogin){
        try {
            Student student = studentRepository.findByUsername(datosLogin.email())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            Authentication authToken = new UsernamePasswordAuthenticationToken(datosLogin.email(), datosLogin.password());
            var usuarioAutenticado = authenticationManager.authenticate(authToken);
            var JWTtoken = tokenService.generarToken((Student) usuarioAutenticado.getPrincipal());

            DataJWTToken dataJWTToken = new DataJWTToken(JWTtoken);

            DataresponseLogin dataresponseLogin = new DataresponseLogin(
                    dataJWTToken, new DataListStudents(student),
                    groupsService.listGroupsByStudent(student.getId()));

            return new ResponseEntity<>(dataresponseLogin, HttpStatus.OK);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    @Operation(summary = "Registrar usuario", description = "Registrar usuario")
    @PostMapping("/register")
    public ResponseEntity<?> registerStudent(@RequestBody @Valid DataRegisterStudent data) {
        return new ResponseEntity<>(studentService.save(data), HttpStatus.CREATED);
    }
}
