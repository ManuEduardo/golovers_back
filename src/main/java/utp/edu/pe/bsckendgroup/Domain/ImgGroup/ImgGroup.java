package utp.edu.pe.bsckendgroup.Domain.ImgGroup;

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
@Table(name = "img_group")
public class ImgGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private GroupUtp groupUtp;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Student student;

    @Column(nullable = false, name = "date_time")
    private LocalDateTime dateTime;

    @Column(nullable = false)
    private String src;

    public ImgGroup(DataRegisterImgGroup data){
        this(
            null,
            new GroupUtp(data.groupId()),
            new Student(data.userId()),
            data.dateTime(),
            data.src()
        );
    }
    public ImgGroup(DataUpdateImgGroup data){
        this.id = data.id();
        if (data.groupId() != null) this.groupUtp = new GroupUtp(data.groupId());
        if (data.userId() != null) this.student = new Student(data.userId());
        if (data.dateTime() != null) this.dateTime = LocalDateTime.parse(data.dateTime());
        if (data.src() != null) this.src = data.src();
    }
}
