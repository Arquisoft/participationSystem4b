package es.uniovi.asw.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.*;

import es.uniovi.asw.business.CitizenService;
import es.uniovi.asw.model.Citizen;


/**
 * Acceso web
 */
@Controller
public class MainController {

    @Autowired
    private CitizenService servicio;
    private Citizen usuario = null;
//
//    // Para ver las paginas entrar en localhost:8080
//    @RequestMapping(value = "/users", method = RequestMethod.GET)
//    public List<Citizen> getUsers() throws NoSuchAlgorithmException, CitizenException {
//        return servicio.findAll();
//    }

    @RequestMapping("/")
    public String index() {
    	//Cerramos sesion usuario a null y lo mandamos al landing
    	//Aunque tambien se inicializa al principio
    	usuario = null;
        return "landing";
    }
    
    //Se le llama al realizar login
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("dni") String dni, @RequestParam("password") String password){
    	//Para cuando la bbdd tenga contraseñas que nos conocemos
    	//usuario = servicio.findLoggableUser(dni , md5(password));
    	
    	usuario = servicio.findByDni(dni);
    	
    	if(usuario != null){
    		if(usuario.isAdmin())
    			return "admin";
    		else
    			return "usuario";
    	} else
    		return fail();
    }
    
    //Aqui se manda siempre que falle algo 
    @RequestMapping("/fail")
    public String fail() {
        return "error";
    }
}
