package es.uniovi.asw.business;

import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.model.exception.CitizenException;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface CitizenService {

	List<Citizen> findAll() throws CitizenException, NoSuchAlgorithmException;

}
