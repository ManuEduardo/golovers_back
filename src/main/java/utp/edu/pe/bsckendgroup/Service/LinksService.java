package utp.edu.pe.bsckendgroup.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utp.edu.pe.bsckendgroup.Domain.GroupUtp.GroupUtp;
import utp.edu.pe.bsckendgroup.Domain.GroupUtp.GroupUtpRepository;
import utp.edu.pe.bsckendgroup.Domain.Link.*;
import utp.edu.pe.bsckendgroup.Domain.Student.Student;
import utp.edu.pe.bsckendgroup.Domain.Student.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LinksService {
    @Autowired
    private GroupUtpRepository groupUtpRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private LinkRepository linkRepository;

    public boolean newLinkGroupUtp(DataRegisterLink data){
        Optional<GroupUtp> idGroupUtp = Optional.ofNullable(groupUtpRepository.findById(data.groupId())
                .orElseThrow(() -> new RuntimeException("Group not found")));
        Optional<Student> idStudent = Optional.ofNullable(studentRepository.findById(data.userId())
                .orElseThrow(() -> new RuntimeException("Student not found")));
        linkRepository.save(new Link(data));
        return true;
    }
    public List<DataListLink> listLinksGroup(Long groupId){
        List<Link> links = linkRepository.findByGroupUtpId(groupId);
        return links.stream().map(DataListLink::new).toList();
    }
    public boolean updateLink(DataUpdateLink data){
        Optional<Link> link = Optional.ofNullable(linkRepository.findById(data.id())
                .orElseThrow(() -> new RuntimeException("Link not found")));
        if (data.groupId() != null) link.get().setGroupUtp(new GroupUtp(data.groupId()));
        linkRepository.save(link.get());
        return true;
    }
}
