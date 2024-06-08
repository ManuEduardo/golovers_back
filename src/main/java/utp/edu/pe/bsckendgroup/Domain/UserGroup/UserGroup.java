package utp.edu.pe.bsckendgroup.Domain.UserGroup;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utp.edu.pe.bsckendgroup.Domain.GroupUtp.GroupUtp;
import utp.edu.pe.bsckendgroup.Domain.Student.Student;
import utp.edu.pe.bsckendgroup.Domain.Role.Role;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_group")
public class UserGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private GroupUtp groupUtp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="role_id", nullable = false)
    private Role role;

    public UserGroup(DataRegisterUserGroup data){
        this(
                null,
                new Student(data.studentId()),
                new GroupUtp(data.groupId()),
                new Role(data.roleId())
        );
    }
    public UserGroup(DataUpdateUserGroup data){
        this.id = data.id();
        if (data.studentId() != null) this.student = new Student(data.studentId());
        if (data.groupId() != null) this.groupUtp = new GroupUtp(data.groupId());
        if (data.roleId() != null) this.role = new Role(data.roleId());
    }
}
