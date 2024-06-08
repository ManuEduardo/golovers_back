package utp.edu.pe.bsckendgroup.Domain.Student;

import jakarta.validation.constraints.NotNull;

public record DataUpdateStudent(
        @NotNull Long id,
        String name,
        String lastName,
        String email,
        String password,
        Integer ciclo,
        Long phone
) {
}
