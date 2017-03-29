package es.uniovi.asw.business;

import java.util.List;

import es.uniovi.asw.model.EstadosPropuesta;
import es.uniovi.asw.model.Propuesta;

public interface PropuestaService {

	public List<Propuesta> findAll();

	public Propuesta findById(long id);

	public Propuesta findByCitizen(String dni);

	public void save(Propuesta propuesta);
	
	public void update(Propuesta propuesta);

	public List<Propuesta> findByEstado(EstadosPropuesta estado);

}
