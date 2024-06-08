package utp.edu.pe.bsckendgroup.Domain.ImgGroup;

import jakarta.validation.constraints.NotNull;

public record DataUpdateImgGroup(
    @NotNull Long id,
    Long groupId,
    Long userId,
    String dateTime,
    String src
) {
}
