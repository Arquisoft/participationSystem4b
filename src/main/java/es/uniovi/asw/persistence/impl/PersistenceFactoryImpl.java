package es.uniovi.asw.persistence.impl;

import es.uniovi.asw.persistence.CitizenRepository;
import es.uniovi.asw.persistence.PersistenceFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

@Service
@EnableJpaRepositories("es.uniovi.asw.persistence")
public class PersistenceFactoryImpl implements PersistenceFactory {

	@Autowired
	private CitizenRepository citizenRepository;

	@Override
	public CitizenRepository newCitizenRepository() {
		return citizenRepository;
	}
}
