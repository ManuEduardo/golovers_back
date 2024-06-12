package utp.edu.pe.bsckendgroup.Domain.Task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query(value = """
        SELECT new utp.edu.pe.bsckendgroup.Domain.Task.DataParticipationStudent(
            f.id,
            SUM(t.priority),
            (SELECT SUM(t2.priority) FROM Task t2 WHERE t2.kanban.id = :idKanban)
        )
        FROM Task t
        JOIN t.finishStudent f
        WHERE t.kanban.id = :idKanban
        GROUP BY f.id
    """)
    List<DataParticipationStudent> getParticipation(Long idKanban);

    @Query("SELECT t FROM Task t WHERE t.kanban.id = ?1")
    List<Task> findByKanbanId(Long id);

    @Query("SELECT t FROM Task t WHERE t.columKanban.id IN :columnIds")
    List<Task> findByColumnKanbanIdIn(@Param("columnIds") List<Long> columnIds);
}
