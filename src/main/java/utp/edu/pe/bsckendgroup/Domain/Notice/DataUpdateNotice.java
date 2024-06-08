package utp.edu.pe.bsckendgroup.Domain.Notice;

import jakarta.validation.constraints.NotNull;

public record DataUpdateNotice(
        @NotNull Long id,
        Long studentId,
        Long groupId,
        String message,
        String dateTime
) {
}
