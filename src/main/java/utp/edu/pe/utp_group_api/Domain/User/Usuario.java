package utp.edu.pe.utp_group_api.Domain.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utp.edu.pe.utp_group_api.Domain.Role.Role;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    private String name;
    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    )
    private List<Role> roles;

    public Usuario(DatosRegistroUsers datos) {
        this.name = datos.name();
        this.username = datos.username();
        this.password = datos.password();
    }
    public Usuario(DatosActualizarUser datos) {
        this.userId = datos.id();
        if (datos.name() != null) {
            this.name = datos.name();
        }
        if (datos.username() != null) {
            this.username = datos.username();
        }
        if (datos.password() != null) {
            this.password = datos.password();
        }
    }
}
