package utp.edu.pe.bsckendgroup.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utp.edu.pe.bsckendgroup.Domain.ColumnKanban.ColumnKanban;
import utp.edu.pe.bsckendgroup.Domain.ColumnKanban.ColumnKanbanRepository;
import utp.edu.pe.bsckendgroup.Domain.ColumnKanban.DataListColumnKanban;
import utp.edu.pe.bsckendgroup.Domain.Kanban.DataListKanban;
import utp.edu.pe.bsckendgroup.Domain.Kanban.DataRegisterKanban;
import utp.edu.pe.bsckendgroup.Domain.Kanban.Kanban;
import utp.edu.pe.bsckendgroup.Domain.Kanban.KanbanRepository;
import utp.edu.pe.bsckendgroup.Domain.Student.StudentRepository;
import utp.edu.pe.bsckendgroup.Domain.Task.DataListTask;
import utp.edu.pe.bsckendgroup.Domain.Task.DataParticipationStudent;
import utp.edu.pe.bsckendgroup.Domain.Task.Task;
import utp.edu.pe.bsckendgroup.Domain.Task.TaskRepository;
import utp.edu.pe.bsckendgroup.ServicesDto.ColumsKanban;
import utp.edu.pe.bsckendgroup.ServicesDto.DataListKanbanAnColumns;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    public DataListKanbanAnColumns getKanban(Long idGroup) {
        Kanban kanban = kanbanRepository.findByGroupId(idGroup)
                .orElseThrow(() -> new RuntimeException("Kanban not found"));
        DataListKanban dataListKanban = new DataListKanban(kanban.getId(), kanban.getGroupUtp().getId(), kanban.getName());

        List<ColumnKanban> columns = columnKanbanRepository.findByKanbanId(kanban.getId());

        List<ColumsKanban> columnKanbans = new ArrayList<>();
        for (ColumnKanban column : columns) {
            column = columnKanbanRepository.findById(column.getId())
                    .orElseThrow(() -> new RuntimeException("Column not found"));
            List<Task> dataListTask = taskRepository.findByColumnKanbanId(column.getId());
            columnKanbans.add(new ColumsKanban(
                    column.getId(), column.getKanban().getId(), column.getTypeColumn().getId(),
                    column.getColor(), column.getTitle(), column.getOrderColum(),
                    dataListTask.stream().map(DataListTask::new).collect(Collectors.toList())
            ));
        }

        return new DataListKanbanAnColumns(dataListKanban, columnKanbans);
    }


    public List<DataParticipationStudent> getParticipation(Long idKanban) {
        Kanban kanban = kanbanRepository.findById(idKanban)
                .orElseThrow(() -> new RuntimeException("Kanban not found"));
        List<DataParticipationStudent> participationStudents = taskRepository.getPartisipation(kanban.getId());

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
