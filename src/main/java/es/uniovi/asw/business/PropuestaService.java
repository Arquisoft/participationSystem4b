package es.uniovi.asw.business;

import es.uniovi.asw.model.Propuesta;

import java.util.List;

public interface PropuestaService {

	public List<Propuesta> findAll();

	public Propuesta findById(long id);

	public Propuesta findByCitizen(String dni);

	public void save(Propuesta propuesta);
	
	public void update(Propuesta propuesta);

}
