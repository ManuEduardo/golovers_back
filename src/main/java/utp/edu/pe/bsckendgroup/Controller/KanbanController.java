package utp.edu.pe.bsckendgroup.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utp.edu.pe.bsckendgroup.Domain.Kanban.DataRegisterKanban;
import utp.edu.pe.bsckendgroup.Service.KanbanService;

@RestController
@RequestMapping("/kanban")
public class KanbanController {
    @Autowired
    private KanbanService kanbanService;
    @PostMapping("/create")
    public ResponseEntity<?> createKanban(DataRegisterKanban kanban) {
        return ResponseEntity.ok(kanbanService.createKanban(kanban));
    }
    @GetMapping("/five")
    public ResponseEntity<?> fiveKanban(Long idGroup) {
        return ResponseEntity.ok(kanbanService.fiveKanban(idGroup));
    }
    @GetMapping("/list")
    public ResponseEntity<?> getKanban() {
        return ResponseEntity.ok(kanbanService.getKanban());
    }
    @GetMapping("/participation")
    public ResponseEntity<?> getParticipation(Long idKanban) {
        return ResponseEntity.ok(kanbanService.getParticipation(idKanban));
    }
}
