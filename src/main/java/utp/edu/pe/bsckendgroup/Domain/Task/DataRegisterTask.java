package utp.edu.pe.bsckendgroup.Domain.Task;

import jakarta.validation.constraints.NotNull;

public record DataRegisterTask(
        @NotNull Long kanbanId,
        @NotNull String name,
        @NotNull String description,
        @NotNull Long assignedUserId,
        @NotNull Integer priority,
        Long finishUserId
) {
}
