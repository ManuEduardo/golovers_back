package utp.edu.pe.utp_group_api.Domain.Courses;

public record DatosListadoCursos(
        Long cursoId,
        String nombre,
        String descripcion
) {
    public DatosListadoCursos(Curso curso) {
        this(curso.getCursoId(), curso.getNombre(), curso.getDescripcion());
    }
}
