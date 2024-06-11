package utp.edu.pe.bsckendgroup.Domain.Task;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DataUpdateTask(
        @NotNull Long id,
        @NotNull Long columnKanbanId
) {
}
