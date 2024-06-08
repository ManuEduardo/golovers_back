package utp.edu.pe.bsckendgroup.Domain.Notice;

public record DataListNotice(
        Long id,
        Long studentId,
        Long groupId,
        String message,
        String dateTime
) {
    public DataListNotice(Notice data){
        this(
                data.getId(),
                data.getStudent().getId(),
                data.getGroupUtp().getId(),
                data.getMessage(),
                data.getDateTime().toString()
        );
    }
}
