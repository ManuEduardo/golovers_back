package utp.edu.pe.bsckendgroup.Domain.TypeColumn;

import jakarta.validation.constraints.NotNull;

public record DataRegisterTypeColumn(
    @NotNull String name,
    @NotNull String description,
    @NotNull Boolean isEditable
) {
}
