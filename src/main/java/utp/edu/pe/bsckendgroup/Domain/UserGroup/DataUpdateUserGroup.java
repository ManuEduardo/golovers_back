package utp.edu.pe.bsckendgroup.Domain.UserGroup;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataUpdateUserGroup(
        @NotNull Long id,
        Long studentId,
        Long groupId,
        Long roleId
) {
}
