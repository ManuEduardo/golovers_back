package utp.edu.pe.bsckendgroup.Domain.ColumnKanban;

import jakarta.validation.constraints.NotNull;

public record DataUpdateColumnKanban(
        @NotNull Long id,
        Long kanbanId,
        Long typeColumnId,
        String color,
        String title,
        Integer orderColum
) {
}
