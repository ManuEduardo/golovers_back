package utp.edu.pe.bsckendgroup.Domain.Kanban;

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
