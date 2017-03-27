package es.uniovi.asw.business.impl;

import es.uniovi.asw.business.ComentarioService;
import es.uniovi.asw.conf.Factories;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.model.Comentario;
import es.uniovi.asw.model.Propuesta;
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
	public List<Comentario> findAll() {
		return comentarioRepository.findAll();
	}

	@Override
	public Comentario findById(long id) {
		return comentarioRepository.findByID(id);
	}

	@Override
	public Comentario findByCitizen(String dni) {
		return comentarioRepository.findByDni(dni);
	}

	@Override
	@Transactional
	// Métodos @Transactional debe ir en @Service
	public void save(int idCitizen, int idPropuesta, String mensaje) {
		Citizen citizen = factories.getServicesFactory().getCitizenService()
				.findById(idCitizen);
		Propuesta propuesta = factories.getServicesFactory()
				.getPropuestaService().findById(idPropuesta);
		Comentario comentario = new Comentario(citizen, propuesta, mensaje);
		comentarioRepository.save(comentario);
	}
}