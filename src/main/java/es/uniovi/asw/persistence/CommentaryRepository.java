package es.uniovi.asw.persistence;

import es.uniovi.asw.model.Commentary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentaryRepository extends JpaRepository<Commentary, Long> {
	@Query("Select c from Commentary c where c.id=?1")
	Commentary findByID(long id);

	@Query("Select c from Commentary c where c.citizen.dni=?1")
	Commentary findByDni(String dni);
	
	@Query("Select c from Commentary c where c.proposal.id=?1")
	Commentary findByProposalId(long id);
}
