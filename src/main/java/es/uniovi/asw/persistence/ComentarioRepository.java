package es.uniovi.asw.persistence;

import es.uniovi.asw.model.Commentary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ComentarioRepository extends JpaRepository<Commentary, Long> {
	@Query("Select c from Comentario c where c.id=?1")
	Commentary findByID(long id);

	@Query("Select c from Comentario c where c.citizen.dni=?1")
	Commentary findByDni(String dni);
	
	@Query("Select c from Comentario c where c.propuesta.id=?1")
	Commentary findByPropuestaId(long id);
}
