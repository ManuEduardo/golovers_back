package utp.edu.pe.bsckendgroup.Infra.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import utp.edu.pe.bsckendgroup.Domain.ImgGroup.ImgGroupRepository;
import utp.edu.pe.bsckendgroup.Domain.Student.StudentRepository;
import utp.edu.pe.bsckendgroup.Domain.UserGroup.UserGroupRepository;

@Service
public class SecurityService {

    @Autowired
    private ImgGroupRepository imgGroupRepository;

    @Autowired
    private UserGroupRepository userGroupRepository;

    @Autowired
    private StudentRepository groupRoleService;

    public boolean isGroupLeader(Authentication authentication, Long groupId) {
        String username = authentication.getName();
        return imgGroupRepository.findById(groupId)
                .map(imgGroup -> {
                    Long studentId = getStudentIdByUsername(username);
                    return userGroupRepository.getRolesByGroupId(studentId, groupId)
                            .stream()
                            .anyMatch(role -> role.getRoleName().equals("ROLE_LEADER"));
                })
                .orElse(false);
    }

    private Long getStudentIdByUsername(String username) {
        return userGroupRepository.findByEmail(username)
                .map(student -> student.getId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }
}

