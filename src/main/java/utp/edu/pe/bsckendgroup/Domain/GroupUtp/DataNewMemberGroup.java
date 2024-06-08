package utp.edu.pe.bsckendgroup.Domain.GroupUtp;

import jakarta.validation.constraints.NotNull;

public record DataNewMemberGroup(
    @NotNull Long idStudent,
    @NotNull String code
) {
}
