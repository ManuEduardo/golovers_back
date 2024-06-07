package utp.edu.pe.utp_group_api.Domain.User;

public record DatosListadoUsers(
        Long id,
        String name,
        String username,
        String password
) {
    public DatosListadoUsers(Usuario user) {
        this(user.getUserId(), user.getName(), user.getUsername(), user.getPassword());
    }
}
