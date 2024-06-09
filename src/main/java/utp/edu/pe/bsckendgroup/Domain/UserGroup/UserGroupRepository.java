package utp.edu.pe.bsckendgroup.Domain.UserGroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import utp.edu.pe.bsckendgroup.Domain.Student.Student;

import javax.management.relation.Role;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {

    @Query("SELECT ug FROM UserGroup ug WHERE ug.groupUtp.id = ?1")
    List<UserGroup> findByUserIdAndGroupId( Long groupId);

    @Query("SELECT ug FROM UserGroup ug WHERE ug.student.id = ?1")
    List<UserGroup> getGroupMembershipsByUsername(Long id);

    @Query("SELECT ug.role FROM UserGroup ug WHERE ug.student.id = ?1 AND ug.groupUtp.id = ?2")
    Collection<Role> getRolesByGroupId(Long studentId, Long groupId);


    @Query("SELECT ug FROM UserGroup ug WHERE ug.student.email = ?1")
    Optional<Student> findByEmail(String username);

    @Query("SELECT ug FROM UserGroup ug WHERE ug.student.id = ?1")
    Collection<UserGroup> findByUserId(Long id);
}
