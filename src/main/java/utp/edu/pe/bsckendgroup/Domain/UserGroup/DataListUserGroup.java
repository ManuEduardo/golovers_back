package utp.edu.pe.bsckendgroup.Domain.UserGroup;

public record DataListUserGroup(
        Long id,
        Long studentId,
        Long groupId,
        Long roleId
) {
    public DataListUserGroup(UserGroup data){
        this(
                data.getId(),
                data.getStudent().getId(),
                data.getGroupUtp().getId(),
                data.getRole().getId()
        );
    }
}
