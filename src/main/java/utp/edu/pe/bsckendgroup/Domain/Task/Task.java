package utp.edu.pe.bsckendgroup.Domain.Task;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utp.edu.pe.bsckendgroup.Domain.ColumnKanban.ColumnKanban;
import utp.edu.pe.bsckendgroup.Domain.Kanban.Kanban;
import utp.edu.pe.bsckendgroup.Domain.Student.Student;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "colum_kanban_id", nullable = false)
    private ColumnKanban columKanban;

    @ManyToOne
    @JoinColumn(name = "kanban_id", nullable = false)
    private Kanban kanban;

    @Column(nullable = false)
    private String name;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_user_id")
    private Student assignedUser;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private int priority;

    @Column(nullable = false)
    private LocalDateTime lastUpdated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="finish_user_id")
    private Student finishStudent;

    @Column(nullable = false, name="limit_time")
    private LocalDateTime limitTime;

    public Task(DataRegisterTask data) {
        this(
                null,
                new ColumnKanban(data.columnKanbanId()),
                new Kanban(data.kanbanId()),
                data.name(),
                data.description(),
                new Student(data.assignedUserId()),
                data.date(),
                data.priority(),
                data.lastUpdated(),
                new Student(data.finishUserId()),
                data.limitTime()
        );
    }
    public Task(DataUpdateTask data) {
        this.id = data.id();
        if (data.columnKanbanId() != null) this.columKanban = new ColumnKanban(data.columnKanbanId());
        if (data.kanbanId() != null) this.kanban = new Kanban(data.kanbanId());
        if (data.name() != null) this.name = data.name();
        if (data.description() != null) this.description = data.description();
        if (data.assignedUserId() != null) this.assignedUser = new Student(data.assignedUserId());
        if (data.date() != null) this.date = data.date();
        if (data.priority() != null) this.priority = data.priority();
        if (data.lastUpdated() != null) this.lastUpdated = data.lastUpdated();
        if (data.finishUserId() != null) this.finishStudent = new Student(data.finishUserId());
        if (data.limitTime() != null) this.limitTime = data.limitTime();
    }
}