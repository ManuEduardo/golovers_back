package utp.edu.pe.bsckendgroup.Domain.Student;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "last_name")
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private int ciclo;

    @Column(nullable = false)
    private Long phone;

    public Student(Long id) {
        if (id != null) {
            this.id = id;
        }
    }

    public Student(DataRegisterStudent data) {
        this(
                null,
                data.name(),
                data.lastName(),
                data.email(),
                data.password(),
                data.ciclo(),
                data.phone()
        );
    }
    public Student(DataUpdateStudent data){
        this.id = data.id();
        if (data.name() != null) this.name = data.name();
        if (data.lastName() != null) this.lastName = data.lastName();
        if (data.email() != null) this.email = data.email();
        if (data.password() != null) this.password = data.password();
        if (data.ciclo() != null) this.ciclo = data.ciclo();
        if (data.phone() != null) this.phone = data.phone();
    }
}
