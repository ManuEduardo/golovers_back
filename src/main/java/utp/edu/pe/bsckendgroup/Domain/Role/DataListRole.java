package utp.edu.pe.bsckendgroup.Domain.Role;

public record DataListRole(
    Long id,
    String name
) {
    public DataListRole(Role role) {
        this(
                role.getId(),
                role.getName()
        );
    }
}
