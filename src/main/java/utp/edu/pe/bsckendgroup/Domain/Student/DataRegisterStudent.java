package utp.edu.pe.bsckendgroup.Domain.Student;

import jakarta.validation.constraints.NotNull;

public record DataRegisterStudent(
        @NotNull String name,
        @NotNull String lastName,
        @NotNull String email,
        @NotNull String password,
        @NotNull Integer ciclo,
        @NotNull Long phone
) {
}
