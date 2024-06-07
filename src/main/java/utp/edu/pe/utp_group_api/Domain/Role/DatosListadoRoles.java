package utp.edu.pe.utp_group_api.Domain.Role;

public record DatosListadoRoles(
    Long id,
    String name
) {
    public DatosListadoRoles(Role role) {
        this(role.getRoleId(), role.getName());
    }
}
