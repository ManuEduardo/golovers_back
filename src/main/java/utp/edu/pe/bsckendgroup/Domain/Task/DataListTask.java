package utp.edu.pe.bsckendgroup.Domain.Task;

import java.time.LocalDateTime;

public record DataListTask(
        Long id,
        Long columnKanbanId,
        Long kanbanId,
        String name,
        String description,
        Long assignedUserId,
        LocalDateTime date,
        Integer priority,
        LocalDateTime lastUpdated
) {
    public DataListTask(Task data) {
        this(
                data.getId(),
                data.getColumKanban().getId(),
                data.getKanban().getId(),
                data.getName(),
                data.getDescription(),
                data.getAssignedUser().getId(),
                data.getDate(),
                data.getPriority(),
                data.getLastUpdated()
        );
    }
}
