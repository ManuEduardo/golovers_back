package utp.edu.pe.bsckendgroup.Domain.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    UserDetails findByEmail(String email);

    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findByUsername(String email);

}
