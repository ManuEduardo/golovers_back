package utp.edu.pe.bsckendgroup.Domain.Kanban;

import utp.edu.pe.bsckendgroup.Domain.ColumnKanban.DataListColumnKanban;

public record DataListKanban(
        Long id,
        Long groupId,
        String name
) {
    public DataListKanban (Kanban data) {
        this(
                data.getId(),
                data.getGroupUtp().getId(),
                data.getName()
        );
    }
}
