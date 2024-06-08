package utp.edu.pe.bsckendgroup.Domain.ImgGroup;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DataRegisterImgGroup(
    @NotNull Long groupId,
    @NotNull Long userId,
    @NotNull LocalDateTime dateTime,
    @NotNull String src
) {
}
