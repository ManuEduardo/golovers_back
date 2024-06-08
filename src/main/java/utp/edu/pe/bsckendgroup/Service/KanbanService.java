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
import utp.edu.pe.bsckendgroup.Domain.Task.TaskRepository;
import utp.edu.pe.bsckendgroup.ServicesDto.ColumsKanban;
import utp.edu.pe.bsckendgroup.ServicesDto.DataListKanbanAnColumns;

import java.util.List;
import java.util.Optional;

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
        Optional<Kanban> kanbans = Optional.ofNullable(kanbanRepository.findByGroupId(idGroup)
                .orElseThrow(() -> new RuntimeException("Kanban not found")));
        DataListKanban kanban = new DataListKanban(kanbans.get().getId(), kanbans.get().getGroupUtp().getId(), kanbans.get().getName());
        List<ColumnKanban> columns = columnKanbanRepository.findByKanbanId(kanbans.get().getId());

        List<DataListTask> tasks = taskRepository.findByKanbanId(kanbans.get().getId());
        List<ColumsKanban> columnsList = columns.stream()
                .map(column -> new ColumsKanban(column.getId(), column.getKanban().getId(), column.getTypeColumn().getId(),
                        column.getColor(), column.getTitle(), column.getOrderColum(), tasks)).toList();
        return new DataListKanbanAnColumns(kanban, columnsList);
    }

    public List<DataParticipationStudent> getParticipation(Long idKanban) {
        Kanban kanban = kanbanRepository.findById(idKanban)
                .orElseThrow(() -> new RuntimeException("Kanban not found"));
        List<DataParticipationStudent> participationStudents =  taskRepository.getPartisipation(kanban.getId());
        return participationStudents;
    }

}
