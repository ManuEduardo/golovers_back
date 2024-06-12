package utp.edu.pe.bsckendgroup.Domain.Notice;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utp.edu.pe.bsckendgroup.Domain.GroupUtp.GroupUtp;
import utp.edu.pe.bsckendgroup.Domain.Student.Student;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notice")
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private GroupUtp groupUtp;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false, name = "date_time")
    private LocalDateTime dateTime;

    @Column(nullable = false)
    private String affair;

    public Notice(DataRegisterNotice data){
        this(
                null,
                new Student(data.studentId()),
                new GroupUtp(data.groupId()),
                data.message(),
                LocalDateTime.now(),
                data.affair()
        );
    }
    public Notice(DataUpdateNotice data){
        this.id = data.id();
        if(data.studentId() != null) this.student = new Student(data.studentId());
        if(data.groupId() != null) this.groupUtp = new GroupUtp(data.groupId());
        if(data.message() != null) this.message = data.message();
        if(data.affair() != null) this.affair = data.affair();
    }
}
