package utp.edu.pe.bsckendgroup.Domain.GroupUtp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GroupUtpRepository extends JpaRepository<GroupUtp, Long> {
    @Query("SELECT g FROM GroupUtp g WHERE g.name = ?1")
    Optional<GroupUtp> findByName(String name);

    @Query("SELECT g FROM GroupUtp g WHERE g.code = ?1")
    Optional<GroupUtp> findByCode(String code);

    @Query("""
        SELECT g FROM GroupUtp g
            JOIN UserGroup ug ON g.id = ug.groupUtp.id
            WHERE ug.student.id = ?1
    """)
    List<DataListGroupUtp> findByStudent(Long idStudent);
}
