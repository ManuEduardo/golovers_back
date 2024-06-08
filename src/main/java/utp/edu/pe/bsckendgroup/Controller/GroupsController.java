package utp.edu.pe.bsckendgroup.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.bsckendgroup.Domain.GroupUtp.DataListGroupUtp;
import utp.edu.pe.bsckendgroup.Domain.GroupUtp.DataNewMemberGroup;
import utp.edu.pe.bsckendgroup.Domain.GroupUtp.DataRegisterGroupUtp;
import utp.edu.pe.bsckendgroup.Service.GroupsService;

@RestController
@RequestMapping("/groups")
public class GroupsController {
    @Autowired
    private GroupsService groupsService;

    @PostMapping("/create")
    public ResponseEntity<?> createGroup(@RequestBody @Valid DataRegisterGroupUtp group) {
        DataListGroupUtp newGroup = groupsService.createGroup(group);
        if (newGroup != null) return new ResponseEntity<>(newGroup, HttpStatus.CREATED);
        return new ResponseEntity<>("Group already exists", HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/newMember")
    public ResponseEntity<?> joinGroup(@RequestBody @Valid DataNewMemberGroup data) {
        if (groupsService.joinGroup(data)) return new ResponseEntity<>("Joined group", HttpStatus.OK);
        return new ResponseEntity<>("Group not found", HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/list")
    public ResponseEntity<?> getGroups() {
        return ResponseEntity.ok(groupsService.getGroups());
    }
    @GetMapping("/list/{code}")
    public ResponseEntity<?> getGroupByCode(@PathVariable String code) {
        return ResponseEntity.ok(groupsService.getGroupByCode(code));
    }
}
