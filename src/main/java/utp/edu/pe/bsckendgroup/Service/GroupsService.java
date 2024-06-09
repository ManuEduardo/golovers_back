package utp.edu.pe.bsckendgroup.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utp.edu.pe.bsckendgroup.Domain.ColumnKanban.ColumnKanban;
import utp.edu.pe.bsckendgroup.Domain.ColumnKanban.ColumnKanbanRepository;
import utp.edu.pe.bsckendgroup.Domain.ColumnKanban.DataRegisterColumnKanban;
import utp.edu.pe.bsckendgroup.Domain.GroupUtp.*;
import utp.edu.pe.bsckendgroup.Domain.Kanban.Kanban;
import utp.edu.pe.bsckendgroup.Domain.Kanban.KanbanRepository;
import utp.edu.pe.bsckendgroup.Domain.Student.Student;
import utp.edu.pe.bsckendgroup.Domain.Student.StudentRepository;
import utp.edu.pe.bsckendgroup.Domain.TypeColumn.TypeColumn;
import utp.edu.pe.bsckendgroup.Domain.TypeColumn.TypeColumnRepository;
import utp.edu.pe.bsckendgroup.Domain.UserGroup.DataRegisterUserGroup;
import utp.edu.pe.bsckendgroup.Domain.UserGroup.UserGroup;
import utp.edu.pe.bsckendgroup.Domain.UserGroup.UserGroupRepository;

import java.util.*;

@Service
public class GroupsService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TypeColumnRepository typeColumnRepository;
    @Autowired
    private GroupUtpRepository groupUtpRepository;
    @Autowired
    private UserGroupRepository userGroupRepository;
    @Autowired
    private KanbanRepository kanbanRepository;
    @Autowired
    private ColumnKanbanRepository columnKanbanRepository;

    public DataListGroupUtp createGroup(DataRegisterGroupUtp group) {
        Student student = studentRepository.findById(group.studentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        if (groupUtpRepository.findByName(group.name()).isPresent()) {
            return null;
        }

        GroupUtp newGroupUtp = new GroupUtp(group);
        newGroupUtp.setCode(generateUniqueCode());
        groupUtpRepository.save(newGroupUtp);

        Kanban kanban = createKanban(newGroupUtp.getId());
        if (kanban != null) {
            createColumnsKanban(kanban.getId());
        }

        DataRegisterUserGroup userGroup = new DataRegisterUserGroup(
                student.getId(),
                newGroupUtp.getId(),
                1L
        );
        userGroupRepository.save(new UserGroup(userGroup));

        return new DataListGroupUtp(newGroupUtp);
    }

    public List<DataListGroupUtp> getGroups() {
        return groupUtpRepository.findAll().stream()
                .map(DataListGroupUtp::new)
                .toList();
    }

    public DataListGroupUtp getGroupByCode(String code) {
        GroupUtp groupUtp = groupUtpRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Group not found"));
        return new DataListGroupUtp(groupUtp);
    }

    public DataListGroupUtp joinGroup(DataNewMemberGroup data) {
        Student student = studentRepository.findById(data.idStudent())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Optional<GroupUtp> groupUtp = groupUtpRepository.findByCode(data.code().toLowerCase());
        if (groupUtp.isEmpty()) {
            new RuntimeException("Group not found");
        }
        if (userGroupRepository.findByUserIdAndGroupId(groupUtp.get().getId()).isEmpty()) {
            DataRegisterUserGroup userGroup = new DataRegisterUserGroup(
                    student.getId(),
                    groupUtp.get().getId(),
                    2L
            );
            userGroupRepository.save(new UserGroup(userGroup));
            return new DataListGroupUtp(groupUtp.get());
        }
        return null;
    }

    public List<DataListGroupUtp> getGroupsByStudent(Long idStudent) {
        return groupUtpRepository.findByStudent(idStudent);
    }

    private String generateUniqueCode() {
        String characters = "123456789abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();

        List<GroupUtp> groups = groupUtpRepository.findAll();
        String code;
        boolean isUnique;

        do {
            code = random.ints(5, 0, characters.length())
                    .mapToObj(characters::charAt)
                    .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                    .toString();
            String finalCode = code;
            isUnique = groups.stream().noneMatch(group -> group.getCode().equals(finalCode));
        } while (!isUnique);

        return code;
    }

    private boolean createColumnsKanban(Long idGroup) {
        return kanbanRepository.findById(idGroup)
                .map(kanban -> {
                    List<TypeColumn> typeColumns = typeColumnRepository.findAll();
                    List<DataRegisterColumnKanban> columns = Arrays.asList(
                            new DataRegisterColumnKanban(kanban.getId(), typeColumns.get(0).getId(), "#DFECF5", typeColumns.get(0).getName(), 1),
                            new DataRegisterColumnKanban(kanban.getId(), typeColumns.get(1).getId(), "#DFF5E5", typeColumns.get(1).getName(), 2),
                            new DataRegisterColumnKanban(kanban.getId(), typeColumns.get(2).getId(), "#F9BFB2", typeColumns.get(2).getName(), 3)
                    );
                    columns.forEach(column -> columnKanbanRepository.save(new ColumnKanban(column)));
                    return true;
                })
                .orElse(false);
    }

    private Kanban createKanban(Long idGroup) {
        return groupUtpRepository.findById(idGroup)
                .map(groupUtp -> {
                    String name = "Kanban-" + groupUtp.getName() + "-" + groupUtp.getCode();
                    kanbanRepository.save(new Kanban(null, groupUtp, name));
                    return kanbanRepository.findByName(name);
                })
                .orElse(null);
    }

    public List<DataListGroupUtp> listGroupsByStudent(Long id) {
        return userGroupRepository.findByUserId(id).stream()
                .map(userGroup -> groupUtpRepository.findById(userGroup.getGroupUtp().getId()).get())
                .map(DataListGroupUtp::new)
                .toList();
    }
}
