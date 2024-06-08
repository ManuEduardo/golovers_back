package utp.edu.pe.bsckendgroup.Domain.Task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query(value = """
        SELECT new utp.edu.pe.bsckendgroup.Domain.Task.DataParticipationStudent(SUM(t.priority), f.id)
        FROM Task t
        JOIN t.finishStudent f
        WHERE t.kanban.id = :idKanban
        GROUP BY f.id
    """)
    List<DataParticipationStudent> getPartisipation(Long idKanban);

    @Query("SELECT t FROM Task t WHERE t.kanban.id = ?1")
    List<DataListTask> findByKanbanId(Long id);
}
