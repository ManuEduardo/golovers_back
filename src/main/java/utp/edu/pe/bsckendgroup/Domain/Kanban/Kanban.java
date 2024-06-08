package utp.edu.pe.bsckendgroup.Domain.Kanban;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utp.edu.pe.bsckendgroup.Domain.GroupUtp.GroupUtp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "kanban")
public class Kanban {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private GroupUtp groupUtp;

    @Column(nullable = false)
    private String name;

    public Kanban(Long id) {
        this.id = id;
    }

    public Kanban(DataRegisterKanban data) {
        this(
                null,
                new GroupUtp(data.groupId()),
                data.name()
        );
    }
    public Kanban(DataUpdateKanban data) {
        this.id = data.id();
        if (data.groupId() != null) this.groupUtp = new GroupUtp(data.groupId());
        if (data.name() != null) this.name = data.name();
    }
}
