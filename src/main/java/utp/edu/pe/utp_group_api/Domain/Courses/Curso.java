package utp.edu.pe.utp_group_api.Domain.Courses;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cursos")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "curso_id")
    private Long cursoId;
    private String nombre;
    private String descripcion;

    public Curso(DatosRegistroCurso datos) {
        this.nombre = datos.nombre();
        this.descripcion = datos.descripcion();
    }
    public Curso(DatosActualizarCurso datos) {
        this.cursoId = datos.id();
        if (datos.nombre() != null) {
            this.nombre = datos.nombre();
        }
        if (datos.descripcion() != null) {
            this.descripcion = datos.descripcion();
        }
    }
}
