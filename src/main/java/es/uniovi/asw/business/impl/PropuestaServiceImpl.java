package es.uniovi.asw.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uniovi.asw.business.PropuestaService;
import es.uniovi.asw.model.EstadosPropuesta;
import es.uniovi.asw.model.Propuesta;
import es.uniovi.asw.persistence.PropuestaRepository;

@Service
public class PropuestaServiceImpl implements PropuestaService {
	@Autowired
	private PropuestaRepository propuestaRepository;

	@Override
	public List<Propuesta> findAll() {
		return propuestaRepository.findAll();
	}

	@Override
	public Propuesta findById(long id) {
		return propuestaRepository.findByID(id);
	}

	@Override
	public Propuesta findByCitizen(String dni) {
		return propuestaRepository.findByDni(dni);
	}

	@Override
	public void save(Propuesta propuesta) {
		propuestaRepository.save(propuesta);
	}

	@Override
	public void update(Propuesta propuesta) {
		//Falta Update en repository, sigo tarjeta de Jorge Votacion propuesta
		//propuestaRepository.update(propuesta);
		
	}

	@Override
	public List<Propuesta> findByEstado(EstadosPropuesta estado) {
		return propuestaRepository.findByEstado(estado);
	}

}
