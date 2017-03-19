package es.uniovi.asw.persistence.impl;

import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.persistence.CitizenRepository;
import es.uniovi.asw.persistence.PersistenceFactory;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

@Service
@EnableJpaRepositories("es.uniovi.asw.persistence")
public class PersistenceFactoryImpl implements PersistenceFactory {

	@Autowired
	private CitizenRepository citizenRepository;

	@PersistenceContext
	private EntityManager em;

	@Override
	public CitizenRepository newCitizenRepository() {
		return citizenRepository;
	}

	@Override
	public List<Citizen> findAllCitizens() {
		TypedQuery<Citizen> query = em.createQuery("select a from tcitizens",
				Citizen.class);
		return query.getResultList();
	}

}
