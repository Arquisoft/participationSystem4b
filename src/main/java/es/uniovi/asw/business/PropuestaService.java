package es.uniovi.asw.business;

import java.util.List;

import es.uniovi.asw.model.EstadosPropuesta;
import es.uniovi.asw.model.Proposal;

public interface PropuestaService {

	public List<Proposal> findAll();

	public Proposal findById(long id);

	public Proposal findByCitizen(String dni);

	public void save(Proposal propuesta);
	
	public void update(Proposal propuesta);

	public List<Proposal> findByEstado(EstadosPropuesta estado);

}
