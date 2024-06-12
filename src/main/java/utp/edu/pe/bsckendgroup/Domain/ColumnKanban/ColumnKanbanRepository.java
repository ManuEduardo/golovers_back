package utp.edu.pe.bsckendgroup.Domain.ColumnKanban;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ColumnKanbanRepository extends JpaRepository<ColumnKanban, Long> {
    @Query("SELECT c FROM ColumnKanban c WHERE c.kanban.id = ?1")
    List<ColumnKanban> findByKanbanId(Long id);

    @Query("SELECT c FROM ColumnKanban c WHERE c.kanban.id = ?1 AND c.typeColumn.name = 'Start'")
    Optional<ColumnKanban> findPrimaryColumn(Long aLong);

    @Query("SELECT c FROM ColumnKanban c WHERE c.kanban.id = ?1 AND c.orderColum = ?2")
    Optional<ColumnKanban> findByIdOrder(Long id, Long aLong);
}
