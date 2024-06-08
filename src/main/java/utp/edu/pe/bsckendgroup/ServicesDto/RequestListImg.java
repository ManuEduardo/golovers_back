package utp.edu.pe.bsckendgroup.ServicesDto;

import jakarta.validation.constraints.NotNull;

public record RequestListImg(
        @NotNull Long groupId,
        @NotNull Long userId
) {
}
