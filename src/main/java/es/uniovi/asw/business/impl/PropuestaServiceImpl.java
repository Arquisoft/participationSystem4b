package es.uniovi.asw.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uniovi.asw.business.PropuestaService;
import es.uniovi.asw.model.EstadosPropuesta;
import es.uniovi.asw.model.Proposal;
import es.uniovi.asw.persistence.PropuestaRepository;

@Service
public class PropuestaServiceImpl implements PropuestaService {
	@Autowired
	private PropuestaRepository propuestaRepository;

	@Override
	public List<Proposal> findAll() {
		return propuestaRepository.findAll();
	}

	@Override
	public Proposal findById(long id) {
		return propuestaRepository.findByID(id);
	}

	@Override
	public Proposal findByCitizen(String dni) {
		return propuestaRepository.findByDni(dni);
	}

	@Override
	public void save(Proposal propuesta) {
		propuestaRepository.save(propuesta);
	}

	@Override
	public void update(Proposal propuesta) {
		//Falta Update en repository, sigo tarjeta de Jorge Votacion propuesta
		//propuestaRepository.update(propuesta);
		
	}

	@Override
	public List<Proposal> findByEstado(EstadosPropuesta estado) {
		return propuestaRepository.findByEstado(estado);
	}

}
