package utp.edu.pe.utp_group_api.Domain.User;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarUser(
        @NotNull Long id,
        String name,
        String username,
        String password
) {
}
