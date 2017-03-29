package es.uniovi.asw.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.uniovi.asw.model.EstadosPropuesta;
import es.uniovi.asw.model.Propuesta;

public interface PropuestaRepository extends JpaRepository<Propuesta, Long> {
	@Query("Select a from Propuesta a where a.id=?1")
	Propuesta findByID(long id);

	@Query("Select a.propuesta from Comentario a where a.citizen.dni=?1")
	Propuesta findByDni(String dni);

	@Query("Select p from Propuesta p where p.estado=?1")
	List<Propuesta> findByEstado(EstadosPropuesta status);
}
