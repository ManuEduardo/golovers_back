package utp.edu.pe.bsckendgroup.Domain.ColumnKanban;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utp.edu.pe.bsckendgroup.Domain.Kanban.Kanban;
import utp.edu.pe.bsckendgroup.Domain.TypeColumn.TypeColumn;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "colum_kanban")
public class ColumnKanban {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "kanban_id", nullable = false)
    private Kanban kanban;

    @ManyToOne
    @JoinColumn(name = "type_column_id", nullable = false)
    private TypeColumn typeColumn;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int orderColum;

    public ColumnKanban(DataRegisterColumnKanban data) {
        this.kanban = new Kanban(data.kanbanId());
        this.typeColumn = new TypeColumn(data.typeColumnId());
        this.color = data.color();
        this.title = data.title();
        this.orderColum = data.orderColum();
    }
    public ColumnKanban(DataUpdateColumnKanban data) {
        this.id = data.id();
        if (data.kanbanId() != null) this.kanban = new Kanban(data.kanbanId());
        if (data.typeColumnId() != null) this.typeColumn = new TypeColumn(data.typeColumnId());
        if (data.color() != null) this.color = data.color();
        if (data.title() != null) this.title = data.title();
        if (data.orderColum() != null) this.orderColum = data.orderColum();
    }

    public ColumnKanban(Long id) {
        if (id != null) this.id = id;
    }
}
