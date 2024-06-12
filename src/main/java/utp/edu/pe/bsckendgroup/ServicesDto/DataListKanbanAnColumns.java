package utp.edu.pe.bsckendgroup.ServicesDto;

import utp.edu.pe.bsckendgroup.Domain.Kanban.DataListKanban;

import java.util.List;

public record DataListKanbanAnColumns(
        DataListKanban data,
        List<DataResponseColumnsKanban> columns
) {
}
