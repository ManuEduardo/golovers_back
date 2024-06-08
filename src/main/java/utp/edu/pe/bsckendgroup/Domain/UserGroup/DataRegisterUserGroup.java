package utp.edu.pe.bsckendgroup.Domain.UserGroup;

import jakarta.validation.constraints.NotNull;

public record DataRegisterUserGroup(
        @NotNull Long studentId,
        @NotNull Long groupId,
        @NotNull Long roleId
) {
}
