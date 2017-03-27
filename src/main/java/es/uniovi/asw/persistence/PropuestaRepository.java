package es.uniovi.asw.persistence;

import es.uniovi.asw.model.Propuesta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PropuestaRepository extends JpaRepository<Propuesta, Long> {
	@Query("Select a from Propuesta a where a.id=?1")
	Propuesta findByID(long id);

	@Query("Select a.propuesta from Comentario a where a.citizen.dni=?1")
	Propuesta findByDni(String dni);
}
