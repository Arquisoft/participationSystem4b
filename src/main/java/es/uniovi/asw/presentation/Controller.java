package es.uniovi.asw.presentation;

import es.uniovi.asw.business.CitizenService;
import es.uniovi.asw.conf.Factories;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.model.exception.CitizenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.util.List;


/**
 * Acceso web
 */
@RestController
public class Controller {
    @Autowired
    private CitizenService servicio;

    // Para ver las paginas entrar en localhost:8080
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<Citizen> getUsers() throws NoSuchAlgorithmException, CitizenException {
        return servicio.findAll();
    }

    @RequestMapping("/")
    public String index() {
        return "Prueba de esto xDDDD";
    }

    @RequestMapping(value = "/prueba1", method = RequestMethod.GET)
    public Citizen prueba1() throws NoSuchAlgorithmException, CitizenException {
        return servicio.findByDni("21313782G");
    }

    @RequestMapping(value = "/prueba2", method = RequestMethod.GET)
    public Citizen prueba2() throws NoSuchAlgorithmException, CitizenException {
        return servicio.findById(1);
    }
}
