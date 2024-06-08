package utp.edu.pe.bsckendgroup.Domain.GroupUtp;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utp.edu.pe.bsckendgroup.Domain.Student.Student;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "group_utp")
public class GroupUtp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Student student;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "class")
    private String className;

    private String description;

    @Column(nullable = false)
    private boolean hasClass;

    private Integer students;

    @Column(nullable = false)
    private String img;

    @Column(nullable = false)
    private String code;

    public GroupUtp(DataRegisterGroupUtp data) {
        this(
                null,
                new Student(data.studentId()),
                data.name(),
                data.className(),
                data.description(),
                data.hasClass(),
                data.students(),
                data.img(),
                null
        );
    }
    public GroupUtp(DataUpdateGroupUtp data){
        this.id = data.id();
        if (data.className() != null) this.className = data.className();
        if (data.description() != null)  this.description = data.description();
        if (data.hasClass() != null)this.hasClass = data.hasClass();
        if (data.students() != null) this.students = data.students();
        if (data.img() != null) this.img = data.img();
    }

    public GroupUtp(Long id) {
        if (id != null) {
            this.id = id;
        }
    }
}
