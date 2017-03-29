package es.uniovi.asw.business.impl;

import es.uniovi.asw.business.ComentarioService;
import es.uniovi.asw.conf.Factories;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.model.Commentary;
import es.uniovi.asw.model.Proposal;
import es.uniovi.asw.persistence.ComentarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ComentarioServiceImpl implements ComentarioService {
	@Autowired
	private ComentarioRepository comentarioRepository;

	@Autowired
	private Factories factories;

	@Override
	public List<Commentary> findAll() {
		return comentarioRepository.findAll();
	}

	@Override
	public Commentary findById(long id) {
		return comentarioRepository.findByID(id);
	}

	@Override
	public Commentary findByCitizen(String dni) {
		return comentarioRepository.findByDni(dni);
	}

	@Override
	@Transactional
	// Métodos @Transactional debe ir en @Service
	public void save(int idCitizen, int idPropuesta, String mensaje) {
		Citizen citizen = factories.getServicesFactory().getCitizenService()
				.findById(idCitizen);
		Proposal propuesta = factories.getServicesFactory()
				.getPropuestaService().findById(idPropuesta);
		Commentary comentario = new Commentary(citizen, propuesta, mensaje);
		comentarioRepository.save(comentario);
	}
}