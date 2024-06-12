package utp.edu.pe.bsckendgroup.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utp.edu.pe.bsckendgroup.Domain.ColumnKanban.ColumnKanban;
import utp.edu.pe.bsckendgroup.Domain.ColumnKanban.ColumnKanbanRepository;
import utp.edu.pe.bsckendgroup.Domain.Kanban.DataListKanban;
import utp.edu.pe.bsckendgroup.Domain.Kanban.DataRegisterKanban;
import utp.edu.pe.bsckendgroup.Domain.Kanban.Kanban;
import utp.edu.pe.bsckendgroup.Domain.Kanban.KanbanRepository;
import utp.edu.pe.bsckendgroup.Domain.Student.StudentRepository;
import utp.edu.pe.bsckendgroup.Domain.Task.DataListTask;
import utp.edu.pe.bsckendgroup.Domain.Task.DataParticipationStudent;
import utp.edu.pe.bsckendgroup.Domain.Task.Task;
import utp.edu.pe.bsckendgroup.Domain.Task.TaskRepository;
import utp.edu.pe.bsckendgroup.ServicesDto.DataResponseColumnsKanban;
import utp.edu.pe.bsckendgroup.ServicesDto.DataListKanbanAnColumns;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class KanbanService {
    @Autowired
    private KanbanRepository kanbanRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ColumnKanbanRepository columnKanbanRepository;

    public boolean createKanban(DataRegisterKanban kanban) {
        Kanban newKanban = new Kanban(kanban);
        kanbanRepository.save(newKanban);
        return true;
    }
    public List<DataListKanban> fiveKanban(Long idGroup) {
        return kanbanRepository.findFiveKanban(idGroup);
    }

    public DataListKanbanAnColumns getKanban(Long kanbanId) {
        Kanban kanban = kanbanRepository.findByGroupId(kanbanId)
                .orElseThrow(() -> new RuntimeException("Kanban not found"));
        DataListKanban dataListKanban = new DataListKanban(kanban.getId(), kanban.getGroupUtp().getId(), kanban.getName());

        List<ColumnKanban> columns = columnKanbanRepository.findByKanbanId(kanban.getId());
        if (columns.isEmpty()) {
            throw new RuntimeException("Columns not found");
        }

        List<Long> columnIds = columns.stream().map(ColumnKanban::getId).collect(Collectors.toList());
        List<Task> allTasks = taskRepository.findByColumnKanbanIdIn(columnIds);
        Map<Long, List<Task>> tasksByColumnId = allTasks.stream()
                .collect(Collectors.groupingBy(task -> task.getColumKanban().getId()));

        List<DataResponseColumnsKanban> columnKanbans = columns.stream().map(column -> {
            List<Task> tasks = tasksByColumnId.getOrDefault(column.getId(), new ArrayList<>());
            List<DataListTask> dataListTasks = tasks.stream().map(DataListTask::new).collect(Collectors.toList());
            return new DataResponseColumnsKanban(
                    column.getId(), column.getKanban().getId(), column.getTypeColumn().getId(),
                    column.getColor(), column.getTitle(), column.getOrderColum(), dataListTasks
            );
        }).collect(Collectors.toList());

        return new DataListKanbanAnColumns(dataListKanban, columnKanbans);
    }



    public List<DataParticipationStudent> getParticipation(Long idKanban) {
        Kanban kanban = kanbanRepository.findById(idKanban)
                .orElseThrow(() -> new RuntimeException("Kanban not found"));
        List<DataParticipationStudent> participationStudents = taskRepository.getParticipation(kanban.getId());

        if (participationStudents.isEmpty()) {
            return participationStudents;
        }
        double totalPriority = participationStudents.stream()
                .mapToDouble(DataParticipationStudent::sumPriority)
                .sum();
        List<DataParticipationStudent> participationInPercentage = participationStudents.stream()
                .map(student -> {
                    double participationPercentage = (student.sumPriority() / totalPriority) * 100;
                    return new DataParticipationStudent(student.studentId(), participationPercentage, totalPriority);
                })
                .collect(Collectors.toList());
        return participationInPercentage;
    }
}
