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

import es.uniovi.asw.conf.Factories;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.model.Commentary;
import es.uniovi.asw.model.EstadosPropuesta;
import es.uniovi.asw.model.Proposal;


/**
 * Acceso web
 */
@Controller
public class MainController {

    @Autowired
    private Factories factory;
    
    private Citizen usuario;
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
    public ModelAndView login(@RequestParam("dni") String dni
    		, @RequestParam("password") String password){
    	//Para cuando la bbdd tenga contraseñas que nos conocemos
    	//usuario = servicio.findLoggableUser(dni , md5(password));

    	usuario = factory.getServicesFactory()
    			.getCitizenService()
    			.findByDni(dni);
    	
    	if(usuario != null){
    		if(usuario.isAdmin())
    			return new ModelAndView("admin"); //la contraseña de admin es "admin"
    		else{
    			List<Proposal> proposals = factory.getServicesFactory()
    					.getProposalService()
    					.findByStatus(EstadosPropuesta.EnTramite);    		
    			return new ModelAndView("usuario")
    					.addObject("proposals", proposals);
    		}
    	} else
    		return fail();
    }
    
    @RequestMapping(path = "/comment", method = RequestMethod.GET)
    public ModelAndView comment(@RequestParam String id){
    	if(usuario != null){
        	List<Commentary> commentaries = factory.getServicesFactory().getCommentaryService()
        			.findByProposal(Long.parseLong(id));
	    	if(commentaries!=null)
	    		return new ModelAndView("comment")
	    				.addObject("commentaries", commentaries)
	    				.addObject("hidden", true);
	    	else
	    		return new ModelAndView("comment")
	    				.addObject("hidden", false);
    	}else
    		return fail();
    }
    
    //Aqui solo llamamos cuando queramos que vaya hacia atras, es decir,
    //nos logeamos como usuario pincha en ver comentarios y pulsa inicio
    @RequestMapping("/usuario")
    public ModelAndView backUser() {
    	List<Proposal> proposals = factory.getServicesFactory()
				.getProposalService()
				.findByStatus(EstadosPropuesta.EnTramite);    		
		return new ModelAndView("usuario")
				.addObject("proposals", proposals);
    }
    
    //Aqui se manda siempre que falle algo 
    @RequestMapping("/fail")
    public ModelAndView fail() {
    	usuario = null;
        return new ModelAndView("error");
    }
}
