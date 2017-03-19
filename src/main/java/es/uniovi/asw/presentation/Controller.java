package es.uniovi.asw.presentation;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.uniovi.asw.conf.Factories;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.model.exception.CitizenException;



/**
 * Acceso web
 */
@RestController
public class Controller {
	@Autowired
	private Factories factories;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<Citizen> getUsers() throws NoSuchAlgorithmException, CitizenException {
		return factories.getServicesFactory().getCitizenService().findAll();
	}
}
