package utp.edu.pe.bsckendgroup.Domain.UserGroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {

    @Query("SELECT ug FROM UserGroup ug WHERE ug.student.id = ?1 AND ug.groupUtp.id = ?2")
    List<UserGroup> findByUserIdAndGroupId(Long studentId, Long groupId);

}
