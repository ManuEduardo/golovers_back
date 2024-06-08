package utp.edu.pe.bsckendgroup.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utp.edu.pe.bsckendgroup.Domain.Student.*;
import utp.edu.pe.bsckendgroup.Infra.DataLoginStudent;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public boolean registerStudent(DataRegisterStudent student) {
        Optional<Student> studentOptional = studentRepository.findByEmail(student.email());
        if (studentOptional.isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        studentRepository.save(new Student(student));
        return true;
    }

    public Student getStudentByEmail(String email) {
        return studentRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public boolean deleteStudent(Long id) {
        studentRepository.deleteById(id);
        return true;
    }

    public boolean updateStudent(DataUpdateStudent student) {
        Student studentToUpdate = studentRepository.findById(student.id())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        studentToUpdate.setName(student.name());
        studentToUpdate.setLastName(student.lastName());
        studentToUpdate.setEmail(student.email());
        studentToUpdate.setPassword(student.password());
        studentToUpdate.setCiclo(student.ciclo());
        studentToUpdate.setPhone(student.phone());
        studentRepository.save(studentToUpdate);
        return true;
    }
    public List<DataListStudents> getStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(DataListStudents::new).toList();
    }

    public Student login(DataLoginStudent data) {
        Student student = studentRepository.findByEmail(data.email())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        if (student.getPassword().equals(data.password())) {
            return student;
        } else {
            throw new RuntimeException("Invalid password");
        }
    }
}
