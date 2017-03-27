package es.uniovi.asw.business;

import es.uniovi.asw.model.Comentario;

import java.util.List;

public interface ComentarioService {

	public List<Comentario> findAll();

	public Comentario findById(long id);

	public Comentario findByCitizen(String dni);

	public void save(int idCitizen, int idPropuesta, String mensaje);
}
