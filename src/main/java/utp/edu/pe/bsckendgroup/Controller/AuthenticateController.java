package utp.edu.pe.bsckendgroup.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.bsckendgroup.Domain.Student.DataRegisterStudent;
import utp.edu.pe.bsckendgroup.Infra.DataLoginStudent;
import utp.edu.pe.bsckendgroup.Service.StudentService;

@RestController
@RequestMapping("/authenticate")
public class AuthenticateController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid DataLoginStudent data) {
        return new ResponseEntity<>(studentService.login(data), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid DataRegisterStudent data) {
        return new ResponseEntity<>(studentService.registerStudent(data), HttpStatus.OK);
    }
}
