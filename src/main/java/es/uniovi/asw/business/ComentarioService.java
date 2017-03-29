package es.uniovi.asw.business;

import es.uniovi.asw.model.Commentary;

import java.util.List;

public interface ComentarioService {

	public List<Commentary> findAll();

	public Commentary findById(long id);

	public Commentary findByCitizen(String dni);

	public void save(int idCitizen, int idPropuesta, String mensaje);
}
