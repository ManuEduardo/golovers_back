package utp.edu.pe.bsckendgroup.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.bsckendgroup.Domain.GroupUtp.DataAddStudent;
import utp.edu.pe.bsckendgroup.Domain.GroupUtp.DataListGroupUtp;
import utp.edu.pe.bsckendgroup.Domain.GroupUtp.DataNewMemberGroup;
import utp.edu.pe.bsckendgroup.Domain.GroupUtp.DataRegisterGroupUtp;
import utp.edu.pe.bsckendgroup.Service.GroupsService;
import utp.edu.pe.bsckendgroup.Service.KanbanService;
import utp.edu.pe.bsckendgroup.ServicesDto.DataListKanbanAnColumns;

@RestController
@RequestMapping("/groups")
@Tag(name = "Groups", description = "Groups")
public class GroupsController {
    @Autowired
    private GroupsService groupsService;
    @Autowired
    private KanbanService kanbanService;

    @Operation(summary = "Create group", description = "Create group")
    @PostMapping("/create")
    public ResponseEntity<?> createGroup(@RequestBody @Valid DataRegisterGroupUtp group) {
        DataListGroupUtp newGroup = groupsService.createGroup(group);
        if (newGroup != null) return new ResponseEntity<>(newGroup, HttpStatus.CREATED);
        return new ResponseEntity<>("Group already exists", HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "Join group", description = "Join group")
    @PostMapping("/newMember")
    public ResponseEntity<?> joinGroup(@RequestBody @Valid DataNewMemberGroup data) {
        DataListGroupUtp group = groupsService.joinGroup(data);
        DataListKanbanAnColumns kanban = kanbanService.getKanban(group.id());
        if (group != null) return new ResponseEntity<>(kanban, HttpStatus.OK);
        return new ResponseEntity<>("Group not found", HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "List groups", description = "List groups")
    @GetMapping("/{id}")
    public ResponseEntity<?> getGroups(@PathVariable Long id) {
        return ResponseEntity.ok(groupsService.getGroupsByStudent(id));
    }

    @Operation(summary = "Get group by code", description = "Get group by code")
    @GetMapping("/list/{code}")
    public ResponseEntity<?> getGroupByCode(@PathVariable String code) {
        return ResponseEntity.ok(groupsService.getGroupByCode(code));
    }

    @Operation(summary = "Get students by group", description = "Get students by group")
    @GetMapping("/students/{id}")
    public ResponseEntity<?> getStudentsByGroup(@PathVariable Long id) {
        return ResponseEntity.ok(groupsService.getStudentsByGroup(id));
    }

    @Operation(summary = "add student", description = "add student with group id")
    @PostMapping("/addStudent")
    public ResponseEntity<?> joinGroupById(@RequestBody @Valid DataAddStudent data) {
        return ResponseEntity.ok(groupsService.addStudentWithGroup(data));
    }
}
