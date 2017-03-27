package es.uniovi.asw.persistence;

import es.uniovi.asw.model.Comentario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
	@Query("Select a from Comentario a where a.id=?1")
	Comentario findByID(long id);

	@Query("Select a from Comentario a where a.citizen.dni=?1")
	Comentario findByDni(String dni);
}
