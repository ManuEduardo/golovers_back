package utp.edu.pe.bsckendgroup.Domain.Notice;

import jakarta.validation.constraints.NotNull;

public record DataRegisterNotice(
        @NotNull Long studentId,
        @NotNull Long groupId,
        @NotNull String message,
        @NotNull String affair
) {
}
