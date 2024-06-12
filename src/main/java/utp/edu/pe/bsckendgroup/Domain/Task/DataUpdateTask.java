package utp.edu.pe.bsckendgroup.Domain.Task;

import jakarta.validation.constraints.NotNull;

public record DataUpdateTask(
        @NotNull Long id,
        @NotNull Long oderColumn
) {
}
