package utp.edu.pe.bsckendgroup.ServicesDto;

import jakarta.validation.constraints.NotNull;

public record DataAlterImgGroup(
        @NotNull Long studentId,
        @NotNull Long groupId,
        @NotNull Long imgId
) {
}
