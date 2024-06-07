package utp.edu.pe.utp_group_api.Domain.Courses;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarCurso(
        @NotNull Long id,
        String nombre,
        String descripcion,
        Integer creditos,
        Integer horas,
        Integer estado
) {
}
