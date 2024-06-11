package utp.edu.pe.bsckendgroup.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.bsckendgroup.Domain.Notice.DataRegisterNotice;
import utp.edu.pe.bsckendgroup.Service.NoticeService;

@RestController
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @PostMapping("/create")
    public ResponseEntity<?>  createNotice(@RequestBody @Valid DataRegisterNotice notice) {
        noticeService.createNotice(notice);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<?> getNotices(@PathVariable Long groupId) {
        return ResponseEntity.ok(noticeService.getNotices(groupId));
    }

    @PostMapping("/student/{id}")
    public ResponseEntity<?> getNoticesByStudent(@PathVariable Long id) {
        return ResponseEntity.ok(noticeService.getNoticesByStudent(id));
    }
}
