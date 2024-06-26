package utp.edu.pe.bsckendgroup.Service;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utp.edu.pe.bsckendgroup.Domain.ColumnKanban.ColumnKanban;
import utp.edu.pe.bsckendgroup.Domain.ColumnKanban.ColumnKanbanRepository;
import utp.edu.pe.bsckendgroup.Domain.Kanban.Kanban;
import utp.edu.pe.bsckendgroup.Domain.Kanban.KanbanRepository;
import utp.edu.pe.bsckendgroup.Domain.Student.Student;
import utp.edu.pe.bsckendgroup.Domain.Student.StudentRepository;
import utp.edu.pe.bsckendgroup.Domain.Task.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ColumnKanbanRepository columnKanbanRepository;
    @Autowired
    private KanbanRepository kanbanRepository;

    public DataListTask create(@NotNull DataRegisterTask data) {

        Kanban kanban = kanbanRepository.findById(data.kanbanId())
                .orElseThrow(() -> new RuntimeException("Kanban not found"));
        ColumnKanban firstColumnKanban = columnKanbanRepository.findPrimaryColumn(kanban.getId())
                .orElseThrow(() -> new RuntimeException("Primary Column not found"));

        Student assignedUser = studentRepository.findById(data.assignedUserId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Task task = new Task(data);
        task.setColumKanban(firstColumnKanban);
        task.setAssignedUser(assignedUser);
        return new DataListTask(taskRepository.save(task));
    }

    public DataListTask update(@NotNull DataUpdateTask data){
        Task task = taskRepository.findById(data.id())
                    .orElseThrow(() -> new RuntimeException("Task not found"));
        ColumnKanban columnKanban = columnKanbanRepository.findByIdOrder(task.getKanban().getId(), data.oderColumn())
                .orElseThrow(() -> new RuntimeException("Column not found"));

        task.setColumKanban(columnKanban);
        return new DataListTask(taskRepository.save(task));
    }

    public void delete(Long id){
            Task task = taskRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Task not found"));
            taskRepository.delete(task);
    }

    public DataListTask findById(Long id){
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        return new DataListTask(task);
    }

    public List<DataListTask> findByKanbanId(Long id){
        return taskRepository.findByKanbanId(id).stream().map(DataListTask::new).toList();
    }

    public List<DataParticipationStudent> finishTask(@NotNull DataFinishTask data) {
        Task task = taskRepository.findById(data.id())
                .orElseThrow(() -> new RuntimeException("Task not found"));
        Student finishStudent = studentRepository.findById(data.finishUserId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        task.setColumKanban(new ColumnKanban(data.columnKanbanId()));
        task.setFinishStudent(finishStudent);
        taskRepository.save(task);

        List<DataParticipationStudent> participationStudents = taskRepository.getParticipation(task.getKanban().getId(), finishStudent.getId());

        if (participationStudents.isEmpty()) {
            return participationStudents;
        }
        double totalPriority = participationStudents.get(0).totalPriority();
        List<DataParticipationStudent> participationInPercentage = participationStudents.stream()
                .map(student -> {
                    double participationPercentage = (student.sumPriority() / totalPriority) * 100;
                    return new DataParticipationStudent(student.studentId(), participationPercentage, totalPriority);
                })
                .collect(Collectors.toList());
        return participationInPercentage;
    }

}
