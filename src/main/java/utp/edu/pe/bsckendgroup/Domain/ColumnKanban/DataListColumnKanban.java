package utp.edu.pe.bsckendgroup.Domain.ColumnKanban;

public record DataListColumnKanban(
        Long id,
        Long kanbanId,
        Long typeColumnId,
        String color,
        String title,
        Integer orderColum
) {
    public DataListColumnKanban (ColumnKanban data) {
        this(
                data.getId(),
                data.getKanban().getId(),
                data.getTypeColumn().getId(),
                data.getColor(),
                data.getTitle(),
                data.getOrderColum()
        );
    }
}
