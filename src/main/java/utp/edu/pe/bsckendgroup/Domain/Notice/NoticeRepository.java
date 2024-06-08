package utp.edu.pe.bsckendgroup.Domain.Notice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    @Query("SELECT n FROM Notice n WHERE n.groupUtp.id = ?1")
    List<Notice> findByGroupId(Long groupId);
}
