package es.uniovi.asw.persistence;

import java.util.List;

import es.uniovi.asw.model.Citizen;

public interface PersistenceFactory {

	CitizenRepository newCitizenRepository();

	List<Citizen> findAllCitizens();

}
