package utp.edu.pe.bsckendgroup.Domain.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT r FROM Role r WHERE r.name = ?1")
    Optional<Role> findByName(String roleLeader);
}
