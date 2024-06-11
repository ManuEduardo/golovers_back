package utp.edu.pe.bsckendgroup.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.bsckendgroup.Domain.Student.DataRegisterStudent;
import utp.edu.pe.bsckendgroup.Domain.Student.DataUpdateStudent;
import utp.edu.pe.bsckendgroup.Service.StudentService;

@RestController
@RequestMapping("/student")
@Tag(name = "Student", description = "Student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Operation(summary = "Register student", description = "Register student")
    @PostMapping("/register")
    public ResponseEntity<?> registerStudent(@RequestBody @Valid DataRegisterStudent student) {
        return ResponseEntity.ok(studentService.registerStudent(student));
    }

    @Operation(summary = "Get student by email", description = "Get student by email")
    @GetMapping("/getByEmail/{email}")
    public ResponseEntity<?> getStudentByEmail(@PathVariable String email) {
        return ResponseEntity.ok(studentService.getStudentByEmail(email));
    }

    @Operation(summary = "Get student by id", description = "Get student by id")
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @Operation(summary = "Delete student", description = "Delete student")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.deleteStudent(id));
    }

    @Operation(summary = "Update student", description = "Update student")
    @PutMapping("/update")
    public ResponseEntity<?> updateStudent(@RequestBody @Valid DataUpdateStudent student) {
        return ResponseEntity.ok(studentService.updateStudent(student));
    }

    @Operation(summary = "Get students", description = "Get students")
    @GetMapping("/list")
    public ResponseEntity<?> getStudents() {
        return ResponseEntity.ok(studentService.getStudents());
    }
}
