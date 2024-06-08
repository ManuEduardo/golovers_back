package utp.edu.pe.bsckendgroup.Domain.Link;

import jakarta.validation.constraints.NotNull;

public record DataRegisterLink(
    @NotNull Long groupId,
    @NotNull String name,
    @NotNull String url,
    @NotNull Long userId
) {
}
