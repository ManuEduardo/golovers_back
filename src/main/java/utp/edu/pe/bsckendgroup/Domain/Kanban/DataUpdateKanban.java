package utp.edu.pe.bsckendgroup.Domain.Kanban;

import jakarta.validation.constraints.NotNull;

public record DataUpdateKanban(
        @NotNull Long id,
        Long groupId,
        String name
) {
}
