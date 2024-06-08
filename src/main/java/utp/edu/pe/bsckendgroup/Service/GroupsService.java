package utp.edu.pe.bsckendgroup.Service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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

import javax.swing.text.html.Option;
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
        Optional<Student> student = Optional.ofNullable(studentRepository.findById(group.studentId())
                .orElseThrow(() -> new RuntimeException("Student not found")));

        Optional<GroupUtp> groupUtpOptional = Optional.ofNullable(groupUtpRepository.findByName(group.name()));

        if (groupUtpOptional.isPresent()) return null;

        GroupUtp newGroupUtp = new GroupUtp(group);
        newGroupUtp.setCode(generateCode());
        groupUtpRepository.save(newGroupUtp);
        GroupUtp groupUtp = groupUtpRepository.findByName(group.name());

        Kanban kanban = createKanban(groupUtp.getId());
        createColumnsKanban(kanban.getId());

        DataRegisterUserGroup userGroup = new DataRegisterUserGroup(
                student.get().getId(),
                groupUtp.getId(),
                1L
        );
        userGroupRepository.save(new UserGroup(userGroup));
        return new DataListGroupUtp(groupUtp);
    }

    public List<DataListGroupUtp> getGroups() {
        List<GroupUtp> groups = groupUtpRepository.findAll();
        return groups.stream().map(DataListGroupUtp::new).toList();
    }

    public DataListGroupUtp getGroupByCode(String code) {
        Optional<GroupUtp> groupUtp = Optional.ofNullable(groupUtpRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Group not found")));
        return groupUtp.map(DataListGroupUtp::new).orElse(null);
    }

    public boolean joinGroup(DataNewMemberGroup data) {
        DataNewMemberGroup newData = new DataNewMemberGroup(data.idStudent(), data.code().toLowerCase());
        Optional<Student> student = Optional.ofNullable(studentRepository.findById(data.idStudent())
                .orElseThrow(() -> new RuntimeException("Student not found")));
        Optional<GroupUtp> groupUtp = Optional.ofNullable(groupUtpRepository.findByCode(newData.code())
                .orElseThrow(() -> new RuntimeException("Group not found")));
        List<UserGroup> userGroupOptional = userGroupRepository.findByUserIdAndGroupId(student.get().getId(), groupUtp.get().getId());

        if (userGroupOptional.isEmpty()) {
            DataRegisterUserGroup userGroup = new DataRegisterUserGroup(
                    student.get().getId(),
                    groupUtp.get().getId(),
                    2L
            );
            userGroupRepository.save(new UserGroup(userGroup));
            return true;
        }
        return false;
    }

    public List<DataListGroupUtp> getGroupsByStudent(Long idStudent) {
        return groupUtpRepository.findByStudent(idStudent);
    }

    private @NotNull String generateCode() {
        String characters = "123456789abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder code = new StringBuilder();

        List<GroupUtp> groups = groupUtpRepository.findAll();
        if (groups.isEmpty()) {
            for (int i = 0; i < 5; i++) {
                int index = random.nextInt(characters.length());
                code.append(characters.charAt(index));
            }
            return code.toString();
        }
        boolean isUnique = false;
        while (!isUnique) {
            for (int i = 0; i < 5; i++) {
                int index = random.nextInt(characters.length());
                code.append(characters.charAt(index));
            }
            isUnique = groups.stream().noneMatch(group -> group.getCode().equals(code.toString()));
        }
        return code.toString();
    }

    private boolean createColumnsKanban(Long idGroup){
        Optional<Kanban> kanban = kanbanRepository.findById(idGroup);
        if (kanban.isEmpty()) return false;
        List<TypeColumn> typeColumns = typeColumnRepository.findAll();
        List<DataRegisterColumnKanban> columns = Arrays.asList(
            new DataRegisterColumnKanban(kanban.get().getId(), typeColumns.get(0).getId(), "#DFECF5",typeColumns.get(0).getName(), 1),
            new DataRegisterColumnKanban(kanban.get().getId(), typeColumns.get(1).getId(), "#DFF5E5 ",typeColumns.get(1).getName(), 2),
            new DataRegisterColumnKanban(kanban.get().getId(), typeColumns.get(2).getId(), "#F9BFB2",typeColumns.get(2).getName(), 3)
        );
        for (DataRegisterColumnKanban column : columns) {
            columnKanbanRepository.save(new ColumnKanban(column));
        }
        return true;
    }
    private @Nullable Kanban createKanban(Long idGroup){
        Optional<GroupUtp> groupUtp = groupUtpRepository.findById(idGroup);
        if (groupUtp.isEmpty()) return null;
        String name = "Kanban-"+groupUtp.get().getName()+"-"+groupUtp.get().getCode();
        kanbanRepository.save(new Kanban(null, groupUtp.get(), name));
        return kanbanRepository.findByName(name);
    }
}
