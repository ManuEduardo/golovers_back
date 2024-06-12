package utp.edu.pe.bsckendgroup.Domain.Task;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DataRegisterTask(
        @NotNull Long kanbanId,
        @NotNull String name,
        @NotNull String description,
        @NotNull Long assignedUserId,
        @NotNull Integer priority,
        @NotNull LocalDateTime limitTime
) {
}
