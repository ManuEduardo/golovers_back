package utp.edu.pe.bsckendgroup.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.bsckendgroup.Domain.GroupUtp.DataListGroupUtp;
import utp.edu.pe.bsckendgroup.Domain.GroupUtp.DataNewMemberGroup;
import utp.edu.pe.bsckendgroup.Domain.GroupUtp.DataRegisterGroupUtp;
import utp.edu.pe.bsckendgroup.Service.GroupsService;
import utp.edu.pe.bsckendgroup.Service.KanbanService;
import utp.edu.pe.bsckendgroup.ServicesDto.DataListKanbanAnColumns;

@RestController
@RequestMapping("/groups")
public class GroupsController {
    @Autowired
    private GroupsService groupsService;
    @Autowired
    private KanbanService kanbanService;

    @PostMapping("/create")
    public ResponseEntity<?> createGroup(@RequestBody @Valid DataRegisterGroupUtp group) {
        DataListGroupUtp newGroup = groupsService.createGroup(group);
        if (newGroup != null) return new ResponseEntity<>(newGroup, HttpStatus.CREATED);
        return new ResponseEntity<>("Group already exists", HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/newMember")
    public ResponseEntity<?> joinGroup(@RequestBody @Valid DataNewMemberGroup data) {
        DataListGroupUtp group = groupsService.joinGroup(data);
        DataListKanbanAnColumns kanban = kanbanService.getKanban(group.id());
        if (group != null) return new ResponseEntity<>(kanban, HttpStatus.OK);
        return new ResponseEntity<>("Group not found", HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getGroups(@PathVariable Long id) {
        return ResponseEntity.ok(groupsService.getGroupsByStudent(id));
    }
    @GetMapping("/list/{code}")
    public ResponseEntity<?> getGroupByCode(@PathVariable String code) {
        return ResponseEntity.ok(groupsService.getGroupByCode(code));
    }
    @GetMapping("/students/{id}")
    public ResponseEntity<?> getStudentsByGroup(@PathVariable Long id) {
        return ResponseEntity.ok(groupsService.getStudentsByGroup(id));
    }
}
