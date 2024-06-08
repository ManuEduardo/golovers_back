package utp.edu.pe.bsckendgroup.Domain.TypeColumn;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "type_column")
public class TypeColumn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false, name = "is_editable")
    private boolean isEditable;

    public TypeColumn(DataRegisterTypeColumn data){
        this(
            null,
            data.name(),
            data.description(),
            data.isEditable()
        );
    }
    public TypeColumn(DataUpdateTypeColumn data){
        this.id = data.id();
        if (data.name() != null) this.name = data.name();
        if (data.description() != null) this.description = data.description();
        if (data.isEditable() != null) this.isEditable = data.isEditable();
    }

    public TypeColumn(Long id) {
        if (id != null) this.id = id;
    }
}
