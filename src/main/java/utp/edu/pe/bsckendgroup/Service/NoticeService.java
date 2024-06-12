package utp.edu.pe.bsckendgroup.Service;

import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utp.edu.pe.bsckendgroup.Domain.GroupUtp.GroupUtp;
import utp.edu.pe.bsckendgroup.Domain.GroupUtp.GroupUtpRepository;
import utp.edu.pe.bsckendgroup.Domain.Notice.DataListNotice;
import utp.edu.pe.bsckendgroup.Domain.Notice.DataRegisterNotice;
import utp.edu.pe.bsckendgroup.Domain.Notice.Notice;
import utp.edu.pe.bsckendgroup.Domain.Notice.NoticeRepository;
import utp.edu.pe.bsckendgroup.Domain.Student.Student;
import utp.edu.pe.bsckendgroup.Domain.Student.StudentRepository;

import java.util.List;

@Service
public class NoticeService {
    @Autowired
    private StudentRepository   studentRepository;
    @Autowired
    private NoticeRepository noticeRepository;
    @Autowired
    private GroupUtpRepository groupUtpRepository;

    @Transactional
    public Boolean createNotice(@NotNull DataRegisterNotice notice) {
        Student student = studentRepository.findById(notice.studentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        GroupUtp group = groupUtpRepository.findById(notice.groupId())
                .orElseThrow(() -> new RuntimeException("Group not found"));
        noticeRepository.save(new Notice(notice));
        return true;
    }

    public List<DataListNotice> getNotices(Long groupId) {
        return noticeRepository.findByGroupId(groupId).stream().map(DataListNotice::new).toList();
    }

    public List<DataListNotice> getNoticesByStudent(Long id) {
        List<Notice> notices = noticeRepository.findByStudentId(id);
        return notices.stream().map(DataListNotice::new).toList();
    }
}
