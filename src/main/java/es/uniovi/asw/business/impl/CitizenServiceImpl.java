package es.uniovi.asw.business.impl;

import es.uniovi.asw.business.CitizenService;
import es.uniovi.asw.business.impl.classes.CitizenFind;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.model.exception.CitizenException;

import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class CitizenServiceImpl implements CitizenService {

	@Override
	public List<Citizen> findAll()
			throws CitizenException, NoSuchAlgorithmException {
		return new CitizenFind().findAll();
	}

}
