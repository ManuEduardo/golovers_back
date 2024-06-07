package utp.edu.pe.utp_group_api.Domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Usuario, Long> {
    @Query("""
        SELECT u
        FROM Usuario u
        WHERE u.username = :username
        """)
    Optional<Usuario> findByUsername(String username);
    @Query("""
        SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END
        FROM Usuario u
        WHERE u.username = :username
        """)
    boolean existsByUsername(@Param("username") String username);
}
