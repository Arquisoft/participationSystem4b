package es.uniovi.asw.persistence.impl;

import es.uniovi.asw.persistence.CitizenRepository;
import es.uniovi.asw.persistence.ComentarioRepository;
import es.uniovi.asw.persistence.PersistenceFactory;
import es.uniovi.asw.persistence.PropuestaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

@Service
@EnableJpaRepositories("es.uniovi.asw.persistence")
public class PersistenceFactoryImpl implements PersistenceFactory {

	@Autowired
	private CitizenRepository citizenRepository;

	@Autowired
	private PropuestaRepository propuestaRepository;

	@Autowired
	private ComentarioRepository comentarioRepository;

	@Override
	public CitizenRepository newCitizenRepository() {
		return citizenRepository;
	}

	@Override
	public PropuestaRepository newPropuestaRepository() {
		return propuestaRepository;
	}

	@Override
	public ComentarioRepository newComentarioRepository() {
		return comentarioRepository;
	}
}
