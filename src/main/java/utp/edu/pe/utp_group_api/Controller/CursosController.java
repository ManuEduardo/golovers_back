package utp.edu.pe.utp_group_api.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.utp_group_api.Domain.Courses.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursosController {
    @Autowired
    private CursosRepository cursosRepository;

    @PostMapping("/crear")
    public ResponseEntity<?> registrarCurso(@RequestBody @Valid DatosRegistroCurso datos) {
        if (cursosRepository.existsByNombre(datos.nombre())) {
            return ResponseEntity.badRequest().body("Curso ya existe");
        }
        Curso curso = new Curso(datos);
        cursosRepository.save(curso);
        return new ResponseEntity<>("Curso registrado", HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<?> obtenerCursos() {
        List<Curso> cursos = cursosRepository.findAll();
        List<DatosListadoCursos> response = cursos.stream()
                .map(DatosListadoCursos::new).toList();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> obtenerCursoPorId(@PathVariable Long id) {
        Curso curso = cursosRepository.findById(id).orElse(null);
        if (curso == null) {
            return ResponseEntity.notFound().build();
        }
        DatosListadoCursos response = new DatosListadoCursos(curso);
        return new ResponseEntity<>(response,  HttpStatus.OK);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizarCurso(@RequestBody @Valid DatosActualizarCurso datos) {
        Curso curso = cursosRepository.findById(datos.id()).orElse(null);
        if (curso == null) {
            return ResponseEntity.notFound().build();
        }
        curso = new Curso(datos);
        cursosRepository.save(curso);
        return new ResponseEntity<>("Curso actualizado", HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarCurso(@PathVariable Long id) {
        Curso curso = cursosRepository.findById(id).orElse(null);
        if (curso == null) {
            return ResponseEntity.notFound().build();
        }
        cursosRepository.delete(curso);
        return new ResponseEntity<>("Curso eliminado", HttpStatus.OK);
    }
}
