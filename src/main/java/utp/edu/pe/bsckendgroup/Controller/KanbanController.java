package utp.edu.pe.bsckendgroup.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.bsckendgroup.Domain.Kanban.DataRegisterKanban;
import utp.edu.pe.bsckendgroup.Service.KanbanService;

@RestController
@RequestMapping("/kanban")
@Tag(name = "Kanban", description = "Kanban")
public class KanbanController {
    @Autowired
    private KanbanService kanbanService;

    @Operation(summary = "Create kanban", description = "Create kanban")
    @PostMapping("/create")
    public ResponseEntity<?> createKanban(DataRegisterKanban kanban) {
        return ResponseEntity.ok(kanbanService.createKanban(kanban));
    }

    @Operation(summary = "Update kanban", description = "Update kanban")
    @GetMapping("/five")
    public ResponseEntity<?> fiveKanban(Long idGroup) {
        return ResponseEntity.ok(kanbanService.fiveKanban(idGroup));
    }

    @Operation(summary = "Get kanban", description = "Get kanban")
    @GetMapping("/list/{kanbanId}")
    public ResponseEntity<?> getKanban(@PathVariable Long kanbanId) {
        return ResponseEntity.ok(kanbanService.getKanban(kanbanId));
    }

    @Operation(summary = "Get participation", description = "Get participation")
    @GetMapping("/participation/{idKanban}")
    public ResponseEntity<?> getParticipation(@PathVariable Long idKanban) {
        return ResponseEntity.ok(kanbanService.getParticipation(idKanban));
    }

}
