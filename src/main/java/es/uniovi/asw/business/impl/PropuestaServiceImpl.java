package es.uniovi.asw.business.impl;

import es.uniovi.asw.business.PropuestaService;
import es.uniovi.asw.model.Propuesta;
import es.uniovi.asw.persistence.PropuestaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

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

}
