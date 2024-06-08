package utp.edu.pe.bsckendgroup.Domain.GroupUtp;

import jakarta.validation.constraints.NotNull;

public record DataUpdateGroupUtp(
    @NotNull Long id,
    Long studentId,
    String name,
    String className,
    String description,
    Boolean hasClass,
    Integer students,
    String img
) {
}
