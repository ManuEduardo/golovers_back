package utp.edu.pe.bsckendgroup.Service;

import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utp.edu.pe.bsckendgroup.Domain.ColumnKanban.ColumnKanban;
import utp.edu.pe.bsckendgroup.Domain.ColumnKanban.ColumnKanbanRepository;
import utp.edu.pe.bsckendgroup.Domain.ColumnKanban.DataRegisterColumnKanban;
import utp.edu.pe.bsckendgroup.Domain.GroupUtp.*;
import utp.edu.pe.bsckendgroup.Domain.Kanban.Kanban;
import utp.edu.pe.bsckendgroup.Domain.Kanban.KanbanRepository;
import utp.edu.pe.bsckendgroup.Domain.Role.Role;
import utp.edu.pe.bsckendgroup.Domain.Role.RoleRepository;
import utp.edu.pe.bsckendgroup.Domain.Student.DataListStudents;
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
    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public DataListGroupUtp createGroup(@NotNull DataRegisterGroupUtp group) {
        Student student = studentRepository.findById(group.studentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        if (groupUtpRepository.findByName(group.name()).isPresent()) {
            return null;
        }

        ensureRolesExist();

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
                roleRepository.findByName("ROLE_LEADER").get().getId()
        );
        userGroupRepository.save(new UserGroup(userGroup));

        return new DataListGroupUtp(newGroupUtp);
    }

    public DataListGroupUtp getGroupByCode(String code) {
        GroupUtp groupUtp = groupUtpRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Group not found"));
        return new DataListGroupUtp(groupUtp);
    }

    public DataListGroupUtp joinGroup(@NotNull DataNewMemberGroup data) {
        Student student = studentRepository.findById(data.idStudent())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        GroupUtp groupUtp = groupUtpRepository.findByCode(data.code().toLowerCase())
                .orElseThrow(() -> new RuntimeException("Group not found"));

        boolean exists = userGroupRepository.existsStudentInGroup(student, groupUtp);

        if (!exists) {
            DataRegisterUserGroup userGroup = new DataRegisterUserGroup(
                    student.getId(),
                    groupUtp.getId(),
                    roleRepository.findByName("ROLE_MEMBER").get().getId()
            );
            userGroupRepository.save(new UserGroup(userGroup));
            return new DataListGroupUtp(groupUtp);
        }
        throw new RuntimeException("Student already in group");
    }


    public List<DataListGroupUtp> getGroupsByStudent(Long idStudent) {
        UserGroup userGroup = userGroupRepository.findByStudent(idStudent)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found"));
        return groupUtpRepository.findByStudent(userGroup.getStudent().getId());
    }

    public List<DataListGroupUtp> listGroupsByStudent(Long id) {
        return userGroupRepository.findByUserId(id).stream()
                .map(userGroup -> groupUtpRepository.findById(userGroup.getGroupUtp().getId()).get())
                .map(DataListGroupUtp::new)
                .toList();
    }

    public List<DataListStudents> getStudentsByGroup(Long id) {
        List<DataListStudents> studentsList = new ArrayList<>();
        return groupUtpRepository.findById(id)
                .map(groupUtp -> {
                    List<UserGroup> userGroups = userGroupRepository.findByGroupId(id);
                    List<Student> students = userGroups.stream()
                            .map(UserGroup::getStudent)
                            .toList();
                    studentsList.addAll(students.stream().map(DataListStudents::new).toList());
                    return studentsList;
                })
                .orElseThrow(() -> new RuntimeException("Group not found"));
    }

    public DataListGroupUtp addStudentWithGroup(@NotNull DataAddStudent data) {
        Optional<Student> student = Optional.ofNullable(studentRepository.findById(data.idStudent())
                .orElseThrow(() -> new RuntimeException("Student not found")));
        GroupUtp groupUtp = groupUtpRepository.findById(data.idGroup())
                .orElseThrow(() -> new RuntimeException("Group not found"));

        if (userGroupRepository.existsStudentInGroup(student.get(), groupUtp)) {
            return null;
        }
        if (userGroupRepository.findByUserIdAndGroupId(student.get().getId(), groupUtp.getId()).isEmpty()) {
            DataRegisterUserGroup userGroup = new DataRegisterUserGroup(
                    student.get().getId(),
                    groupUtp.getId(),
                    roleRepository.findByName("ROLE_MEMBER").get().getId()
            );
            userGroupRepository.save(new UserGroup(userGroup));
            return new DataListGroupUtp(groupUtp);
        }
        return null;
    }

    private void ensureRolesExist() {
        List<String> roleNames = Arrays.asList("ROLE_LEADER", "ROLE_MEMBER");
        for (String roleName : roleNames) {
            if (roleRepository.findByName(roleName).isEmpty()) {
                Role newRole = new Role();
                newRole.setName(roleName);
                roleRepository.save(newRole);
            }
        }
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
                    if (typeColumns.isEmpty()) {
                        List<TypeColumn> defaultTypeColumns = Arrays.asList(
                                new TypeColumn(null, "Start", "Start Column", true),
                                new TypeColumn(null, "In Progress", "In Progress Column", true),
                                new TypeColumn(null, "Done", "Done Column", true)
                        );
                        typeColumns = typeColumnRepository.saveAll(defaultTypeColumns);
                    }

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
                    Kanban kanban = new Kanban(null, groupUtp, name);
                    kanbanRepository.save(kanban);
                    return kanban;
                })
                .orElse(null);
    }
}
