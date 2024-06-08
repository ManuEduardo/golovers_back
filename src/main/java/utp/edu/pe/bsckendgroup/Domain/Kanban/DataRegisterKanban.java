package utp.edu.pe.bsckendgroup.Domain.Kanban;

import jakarta.validation.constraints.NotNull;

public record DataRegisterKanban(
        @NotNull Long groupId,
        @NotNull String name
) {
}
