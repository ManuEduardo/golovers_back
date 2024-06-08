package utp.edu.pe.bsckendgroup.Domain.Link;

import jakarta.validation.constraints.NotNull;

public record DataUpdateLink(
    @NotNull Long id,
    Long groupId,
    String name,
    String url,
    Long userId
) {
}
