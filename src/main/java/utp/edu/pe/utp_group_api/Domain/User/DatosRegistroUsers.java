package utp.edu.pe.utp_group_api.Domain.User;

import jakarta.validation.constraints.NotNull;

public record DatosRegistroUsers(
        @NotNull String name,
        @NotNull String username,
        @NotNull String password
) {
}
