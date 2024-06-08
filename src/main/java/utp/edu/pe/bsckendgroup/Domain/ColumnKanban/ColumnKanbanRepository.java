package utp.edu.pe.bsckendgroup.Domain.ColumnKanban;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ColumnKanbanRepository extends JpaRepository<ColumnKanban, Long> {
    @Query("SELECT c FROM ColumnKanban c WHERE c.kanban.id = ?1")
    List<ColumnKanban> findByKanbanId(Long id);
}
