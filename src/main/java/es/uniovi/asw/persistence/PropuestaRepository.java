package es.uniovi.asw.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.uniovi.asw.model.EstadosPropuesta;
import es.uniovi.asw.model.Proposal;

public interface PropuestaRepository extends JpaRepository<Proposal, Long> {
	@Query("Select a from Propuesta a where a.id=?1")
	Proposal findByID(long id);

	@Query("Select a.propuesta from Comentario a where a.citizen.dni=?1")
	Proposal findByDni(String dni);

	@Query("Select p from Propuesta p where p.estado=?1")
	List<Proposal> findByEstado(EstadosPropuesta status);
}
