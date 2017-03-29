package es.uniovi.asw.presentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import es.uniovi.asw.business.CitizenService;
import es.uniovi.asw.business.PropuestaService;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.model.EstadosPropuesta;
import es.uniovi.asw.model.Propuesta;


/**
 * Acceso web
 */
@Controller
public class MainController {

    @Autowired
    private PropuestaService servicioPropuesta;
    private CitizenService servicioCitizen;
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
    public ModelAndView login(@RequestParam("dni") String dni, @RequestParam("password") String password){
    	//Para cuando la bbdd tenga contraseñas que nos conocemos
    	//usuario = servicio.findLoggableUser(dni , md5(password));

    	usuario = servicioCitizen.findByDni(dni);
    	
    	if(usuario != null){
    		if(usuario.isAdmin())
    			return new ModelAndView("admin");
    		else{
    			List<Propuesta> propuestas = servicioPropuesta.findByEstado(EstadosPropuesta.EnTramite);    		
    			return new ModelAndView("usuario").addObject("propuestas", propuestas);
    		}
    	} else
    		return fail();
    }
    
    //Aqui se manda siempre que falle algo 
    @RequestMapping("/fail")
    public ModelAndView fail() {
    	usuario = null;
        return new ModelAndView("error");
    }
}
