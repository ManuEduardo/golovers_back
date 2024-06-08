package utp.edu.pe.bsckendgroup.Domain.Task;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DataUpdateTask(
        @NotNull Long id,
        Long columnKanbanId,
        Long kanbanId,
        String name,
        String description,
        Long assignedUserId,
        LocalDateTime date,
        Integer priority,
        LocalDateTime lastUpdated,
        Long finishUserId,
        LocalDateTime limitTime
) {
}
