package utp.edu.pe.bsckendgroup.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    @GetMapping("/list/{idGroup}")
    public ResponseEntity<?> getKanban(@PathVariable Long idGroup) {
        return ResponseEntity.ok(kanbanService.getKanban(idGroup));
    }
    @GetMapping("/participation/{idKanban}")
    public ResponseEntity<?> getParticipation(@PathVariable Long idKanban) {
        return ResponseEntity.ok(kanbanService.getParticipation(idKanban));
    }
}
