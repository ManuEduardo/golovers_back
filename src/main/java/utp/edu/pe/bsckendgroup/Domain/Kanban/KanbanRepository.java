package utp.edu.pe.bsckendgroup.Domain.Kanban;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface KanbanRepository extends JpaRepository<Kanban, Long>{
    @Query("""
        SELECT new utp.edu.pe.bsckendgroup.Domain.Kanban.DataListKanban(
                k.id,
                k.groupUtp.id,
                k.name
            )
            FROM Kanban k
            WHERE k.groupUtp.id = :idGroup
            ORDER BY k.id DESC
        """)
    List<DataListKanban> findFiveKanban(Long idGroup);

    @Query("SELECT k FROM Kanban k WHERE k.groupUtp.id = ?1")
    Optional<Kanban> findByGroupId(Long idGroup);

}
