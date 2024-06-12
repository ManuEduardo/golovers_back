package utp.edu.pe.bsckendgroup.Domain.UserGroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import utp.edu.pe.bsckendgroup.Domain.GroupUtp.DataListGroupUtp;
import utp.edu.pe.bsckendgroup.Domain.GroupUtp.GroupUtp;
import utp.edu.pe.bsckendgroup.Domain.Student.Student;

import javax.management.relation.Role;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {

    @Query("SELECT ug FROM UserGroup ug WHERE ug.groupUtp.id = ?1")
    List<UserGroup> findByUserIdAndGroupId( Long groupId);

    @Query("SELECT ug.role FROM UserGroup ug WHERE ug.student.id = ?1 AND ug.groupUtp.id = ?2")
    Collection<Role> getRolesByGroupId(Long studentId, Long groupId);


    @Query("SELECT ug FROM UserGroup ug WHERE ug.student.email = ?1")
    Optional<Student> findByEmail(String username);

    @Query("SELECT ug FROM UserGroup ug WHERE ug.student.id = ?1")
    Collection<UserGroup> findByUserId(Long id);

    @Query("""
        SELECT ug FROM UserGroup ug
            JOIN GroupUtp g ON ug.groupUtp.id = g.id
            WHERE ug.student.id = ?1
        """)
    List<UserGroup> findByStudent(Long idStudent);

    @Query("""
        SELECT ug FROM UserGroup ug
            JOIN GroupUtp g ON ug.groupUtp.id = g.id
            WHERE g.id = ?1
        """)
    List<UserGroup> findByGroupId(Long id);

    @Query("""
        SELECT ug FROM UserGroup ug
            JOIN GroupUtp g ON ug.groupUtp.id = g.id
            WHERE ug.student.id = ?1 AND g.id = ?2
        """)
    Optional<UserGroup> findByUserIdAndGroupId(Long id, Long id1);

    @Query("""
        SELECT CASE WHEN COUNT(ug) > 0 THEN TRUE ELSE FALSE END
        FROM UserGroup ug
        WHERE ug.student = :student AND ug.groupUtp = :groupUtp
    """)
    boolean existsStudentInGroup(@Param("student") Student student, @Param("groupUtp") GroupUtp groupUtp);
}
