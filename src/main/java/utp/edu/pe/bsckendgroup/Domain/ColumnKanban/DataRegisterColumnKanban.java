package utp.edu.pe.bsckendgroup.Domain.ColumnKanban;

import jakarta.validation.constraints.NotNull;

public record DataRegisterColumnKanban(
        @NotNull Long kanbanId,
        @NotNull Long typeColumnId,
        @NotNull String color,
        @NotNull String title,
        @NotNull Integer orderColum
) {
}
