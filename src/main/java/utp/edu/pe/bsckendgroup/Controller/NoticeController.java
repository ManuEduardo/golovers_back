package utp.edu.pe.bsckendgroup.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.bsckendgroup.Domain.Notice.DataRegisterNotice;
import utp.edu.pe.bsckendgroup.Service.NoticeService;

@RestController
@RequestMapping("/notice")
@Tag(name = "Notice", description = "Notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @Operation(summary = "Create notice", description = "Create notice")
    @PostMapping("/create")
    public ResponseEntity<?>  createNotice(@RequestBody @Valid DataRegisterNotice notice) {
        if (noticeService.createNotice(notice)) return new ResponseEntity<>("Create notice exit", HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @Operation  (summary = "Get notices", description = "Get notices")
    @GetMapping("/group/{groupId}")
    public ResponseEntity<?> getNotices(@PathVariable Long groupId) {
        return ResponseEntity.ok(noticeService.getNotices(groupId));
    }

    @Operation(summary = "Get notices by student", description = "Get notices by student")
    @PostMapping("/student/{id}")
    public ResponseEntity<?> getNoticesByStudent(@PathVariable Long id) {
        return ResponseEntity.ok(noticeService.getNoticesByStudent(id));
    }
}
