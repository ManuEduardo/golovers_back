package utp.edu.pe.bsckendgroup.Domain.Notice;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DataRegisterNotice(
        @NotNull Long studentId,
        @NotNull Long groupId,
        @NotNull String message
) {
}
