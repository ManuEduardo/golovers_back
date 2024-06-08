package utp.edu.pe.bsckendgroup.Domain.Link;

public record DataListLink(
    Long id,
    Long groupId,
    String name,
    String url,
    Long userId
) {
    public DataListLink(Link data){
        this(
            data.getId(),
            data.getGroupUtp().getId(),
            data.getName(),
            data.getUrl(),
            data.getStudent().getId()
        );
    }
}
