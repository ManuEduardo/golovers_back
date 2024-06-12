package utp.edu.pe.bsckendgroup.Domain.Link;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LinkRepository extends JpaRepository<Link, Long> {

    @Query("SELECT l FROM Link l WHERE l.groupUtp.id = ?1")
    List<Link> findByGroupUtpId(Long groupId);
}
