package utp.edu.pe.bsckendgroup.Domain.GroupUtp;

public record DataListGroupUtp(
    Long id,
    Long studentId,
    String name,
    String className,
    String description,
    boolean hasClass,
    Integer students,
    String img,
    String code
) {
    public DataListGroupUtp(GroupUtp groupUtp) {
        this(
                groupUtp.getId(),
                groupUtp.getStudent().getId(),
                groupUtp.getName(),
                groupUtp.getClassName(),
                groupUtp.getDescription(),
                groupUtp.isHasClass(),
                groupUtp.getStudents(),
                groupUtp.getImg(),
                groupUtp.getCode()
        );

    }
}
