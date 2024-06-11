package utp.edu.pe.bsckendgroup.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.bsckendgroup.Domain.Student.DataRegisterStudent;
import utp.edu.pe.bsckendgroup.Domain.Student.DataUpdateStudent;
import utp.edu.pe.bsckendgroup.Service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/register")
    public ResponseEntity<?> registerStudent(@RequestBody @Valid DataRegisterStudent student) {
        return ResponseEntity.ok(studentService.registerStudent(student));
    }
    @GetMapping("/getByEmail/{email}")
    public ResponseEntity<?> getStudentByEmail(@PathVariable String email) {
        return ResponseEntity.ok(studentService.getStudentByEmail(email));
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.deleteStudent(id));
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateStudent(@RequestBody @Valid DataUpdateStudent student) {
        return ResponseEntity.ok(studentService.updateStudent(student));
    }
    @GetMapping("/list")
    public ResponseEntity<?> getStudents() {
        return ResponseEntity.ok(studentService.getStudents());
    }
}
