package utp.edu.pe.bsckendgroup.Domain.ImgGroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImgGroupRepository extends JpaRepository<ImgGroup, Long> {
    @Query("""
            SELECT i FROM ImgGroup i
            WHERE i.groupUtp = :groupId
        """)
    List<ImgGroup> findByGroupId(Long groupId);
}
