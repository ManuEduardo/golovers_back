package utp.edu.pe.bsckendgroup.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utp.edu.pe.bsckendgroup.Domain.UserGroup.UserGroup;
import utp.edu.pe.bsckendgroup.Domain.UserGroup.UserGroupRepository;

import java.util.List;

@Service
public class UserRoleGroup {
    @Autowired
    private UserGroupRepository userGroupRepository;

    public boolean isLeader(Long idStudent, Long idGroup) {
        List<UserGroup> user = userGroupRepository.findByUserIdAndGroupId(idStudent, idGroup);
        return user.get(0).getRole().getId() == 1;
    }
}
