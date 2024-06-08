package utp.edu.pe.bsckendgroup.Domain.TypeColumn;

import jakarta.validation.constraints.NotNull;

public record DataUpdateTypeColumn(
    @NotNull Long id,
    String name,
    String description,
    Boolean isEditable
) {
}
