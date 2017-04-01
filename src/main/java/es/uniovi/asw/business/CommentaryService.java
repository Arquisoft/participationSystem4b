package es.uniovi.asw.business;

import es.uniovi.asw.model.Commentary;

import java.util.List;

public interface CommentaryService {

	public List<Commentary> findAll();

	public Commentary findById(long id);

	public Commentary findByCitizen(String dni);

	public void save(Long idCitizen, Long idProposal, String message);

	public List<Commentary> findByProposal(long id);
}
