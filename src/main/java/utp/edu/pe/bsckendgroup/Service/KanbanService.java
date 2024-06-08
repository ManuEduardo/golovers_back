package utp.edu.pe.bsckendgroup.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utp.edu.pe.bsckendgroup.Domain.Kanban.DataListKanban;
import utp.edu.pe.bsckendgroup.Domain.Kanban.DataRegisterKanban;
import utp.edu.pe.bsckendgroup.Domain.Kanban.Kanban;
import utp.edu.pe.bsckendgroup.Domain.Kanban.KanbanRepository;
import utp.edu.pe.bsckendgroup.Domain.Student.StudentRepository;
import utp.edu.pe.bsckendgroup.Domain.Task.DataParticipationStudent;
import utp.edu.pe.bsckendgroup.Domain.Task.TaskRepository;

import java.util.List;

@Service
public class KanbanService {
    @Autowired
    private KanbanRepository kanbanRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TaskRepository taskRepository;

    public boolean createKanban(DataRegisterKanban kanban) {
        Kanban newKanban = new Kanban(kanban);
        kanbanRepository.save(newKanban);
        return true;
    }
    public List<DataListKanban> fiveKanban(Long idGroup) {
        return kanbanRepository.findFiveKanban(idGroup);
    }

    public List<DataListKanban> getKanban() {
        List<Kanban> kanbans = kanbanRepository.findAll();
        return kanbans.stream().map(DataListKanban::new).toList();
    }

    public List<DataParticipationStudent> getParticipation(Long idKanban) {
        Kanban kanban = kanbanRepository.findById(idKanban)
                .orElseThrow(() -> new RuntimeException("Kanban not found"));
        List<DataParticipationStudent> participationStudents =  taskRepository.getPartisipation(kanban.getId());
        return participationStudents;
    }

}
