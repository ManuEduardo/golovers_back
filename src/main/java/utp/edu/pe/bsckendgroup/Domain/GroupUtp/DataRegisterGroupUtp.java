package utp.edu.pe.bsckendgroup.Domain.GroupUtp;

import jakarta.validation.constraints.NotNull;

public record DataRegisterGroupUtp(
    @NotNull Long studentId,
    @NotNull String name,
    @NotNull String className,
    @NotNull String description,
    @NotNull Boolean hasClass,
    @NotNull Integer students,
    @NotNull String img
) {
}
