package utp.edu.pe.utp_group_api.Domain.Courses;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CursosRepository extends JpaRepository<Curso, Long> {
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Curso c WHERE c.nombre = ?1")
    boolean existsByNombre( String nombre);
}
