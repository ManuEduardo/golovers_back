package utp.edu.pe.bsckendgroup.Domain.Link;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utp.edu.pe.bsckendgroup.Domain.GroupUtp.GroupUtp;
import utp.edu.pe.bsckendgroup.Domain.Student.Student;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "link")
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private GroupUtp groupUtp;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String url;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Student student;

    public Link(DataRegisterLink data){
        this(
            null,
            new GroupUtp(data.groupId()),
            data.name(),
            data.url(),
            new Student(data.userId())
        );
    }
    public Link(DataUpdateLink data){
        this.id = data.id();
        if (data.groupId() != null) this.groupUtp = new GroupUtp(data.groupId());
        if (data.name() != null) this.name = data.name();
        if (data.url() != null) this.url = data.url();
        if (data.userId() != null) this.student = new Student(data.userId());
    }
}
