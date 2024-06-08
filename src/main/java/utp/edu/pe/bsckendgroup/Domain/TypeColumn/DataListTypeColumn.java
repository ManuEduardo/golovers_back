package utp.edu.pe.bsckendgroup.Domain.TypeColumn;

public record DataListTypeColumn(
    Long id,
    String name,
    String description,
    Boolean isEditable
) {
    public DataListTypeColumn(TypeColumn data){
        this(
            data.getId(),
            data.getName(),
            data.getDescription(),
            data.isEditable()
        );
    }
}
