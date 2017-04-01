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

	// Con este id controlo la propuesta en la que estoy cuando se navega entre
	// comentarios
	private Long idPropuesta = null;
	//
	// // Para ver las paginas entrar en localhost:8080
	// @RequestMapping(value = "/users", method = RequestMethod.GET)
	// public List<Citizen> getUsers() throws NoSuchAlgorithmException,
	// CitizenException {
	// return servicio.findAll();
	// }

	@RequestMapping("/")
	public String index() {
		// Cerramos sesion usuario a null y lo mandamos al landing
		// Aunque tambien se inicializa al principio
		usuario = null;
		return "landing";
	}

	// Se le llama al realizar login
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam("dni") String dni, @RequestParam("password") String password) {
		// Para cuando la bbdd tenga contraseñas que nos conocemos
		// usuario = servicio.findLoggableUser(dni , md5(password));

		usuario = factory.getServicesFactory().getCitizenService().findByDni(dni);

		if (usuario != null) {
			if (usuario.isAdmin())
				return new ModelAndView("admin"); // la contraseña de admin es
													// "admin"
			else {
				List<Proposal> proposals = factory.getServicesFactory().getProposalService()
						.findByStatus(EstadosPropuesta.EnTramite);
				return new ModelAndView("usuario").addObject("proposals", proposals);
			}
		} else
			return fail();
	}

	@RequestMapping(path = "/comment", method = RequestMethod.GET)
	public ModelAndView comment(@RequestParam String id) {
		if (usuario != null) {
			List<Commentary> commentaries = factory.getServicesFactory().getCommentaryService()
					.findByProposal(Long.parseLong(id));
			if (commentaries != null)
				return new ModelAndView("comment").addObject("commentaries", commentaries).addObject("hidden", true)
						.addObject("id", id);
			else
				return new ModelAndView("comment").addObject("hidden", false).addObject("id", id);
		} else
			return fail();
	}

	// Cuando en el menu de comments pulsamos en añadir un nuevo comentario
	@RequestMapping(path = "/crearComment", method = RequestMethod.GET)
	public ModelAndView crearComment(@RequestParam String id) {
		if (usuario != null) {
			this.idPropuesta = Long.parseLong(id);
			return new ModelAndView("crearComment").addObject("hidden", false);
		} else {
			return fail();
		}
	}

	// Cuando guardamos el comentario
	@RequestMapping(path = "/salvarComment", method = RequestMethod.POST)
	public ModelAndView salvarComment(@RequestParam("comment") String comment) {
		if (idPropuesta != null) {
			if (usuario != null) {
				System.out.println(comment + " \nid de la propuesta: " + Long.toString(idPropuesta));
				// Arreglar la parte del modelo
				// factory.getServicesFactory().getCommentaryService().save(usuario.getId(),
				// idPropuesta, comment);
				return comment(Long.toString(idPropuesta));
			}
		}
		return fail();
	}

	// Aqui solo llamamos cuando queramos que vaya hacia atras, es decir,
	// nos logeamos como usuario pincha en ver comentarios y pulsa inicio
	@RequestMapping("/usuario")
	public ModelAndView backUser() {
		if (usuario != null) {
			if (usuario.isAdmin()) {
				return new ModelAndView("admin");
			} else {
				idPropuesta = null; // cuando se pulsa en incio reseteo el id de
									// la propuesta para evitar la navegacion
									// incorrecta
				List<Proposal> proposals = factory.getServicesFactory().getProposalService()
						.findByStatus(EstadosPropuesta.EnTramite);
				return new ModelAndView("usuario").addObject("proposals", proposals);
			}
		} else {
			return fail();
		}
	}

	// Aqui se manda siempre que falle algo
	@RequestMapping("/fail")
	public ModelAndView fail() {
		usuario = null;
		idPropuesta = null;
		return new ModelAndView("error");
	}
	
	@RequestMapping(path = "/nuevaPropuesta", method = RequestMethod.GET)
	public ModelAndView nuevaPropuesta() {
		return new ModelAndView("nuevaPropuesta");
	}
	
	@RequestMapping(path = "/crearPropuesta", method = RequestMethod.POST)
	public ModelAndView crearPropuesta(@RequestParam("nombre") String nombre, @RequestParam("contenido") String contenido) {

		Proposal propuesta = new Proposal(nombre, contenido, 1000);
		
		factory.getServicesFactory().getProposalService().save(propuesta);
		List<Proposal> proposals = factory.getServicesFactory().getProposalService()
				.findByStatus(EstadosPropuesta.EnTramite);
		
		return new ModelAndView("usuario").addObject("proposals", proposals);
	}
	
	@RequestMapping(path = "/votarPositivo", method = RequestMethod.GET)
	public ModelAndView votarPositivo(@RequestParam("idPropuesta") String idPropuesta) {

		Proposal propuesta = factory.getServicesFactory().getProposalService().findById(Long.parseLong(idPropuesta));
		System.out.println("Votando positivo - VotosA: " + propuesta.getValoration());
		propuesta.positiveVote();
		factory.getServicesFactory().getProposalService().update(propuesta);
		comprobarNumeroVotos();
		List<Proposal> proposals = factory.getServicesFactory().getProposalService()
				.findByStatus(EstadosPropuesta.EnTramite);
		System.out.println("Votando positivo - VotosB: " + propuesta.getValoration());
		return new ModelAndView("usuario").addObject("proposals", proposals);
	}
	
	@RequestMapping(path = "/votarNegativo", method = RequestMethod.GET)
	public ModelAndView votarNegativo(@RequestParam("idPropuesta") String idPropuesta) {

		Proposal propuesta = factory.getServicesFactory().getProposalService().findById(Long.parseLong(idPropuesta));				
		System.out.println("Votando negativo - VotosA: " + propuesta.getValoration());
		propuesta.negativeVote();	
		comprobarNumeroVotos();
		factory.getServicesFactory().getProposalService().update(propuesta);
		List<Proposal> proposals = factory.getServicesFactory().getProposalService()
				.findByStatus(EstadosPropuesta.EnTramite);	
		System.out.println("Votando negativo - VotosA: " + propuesta.getValoration());
		return new ModelAndView("usuario").addObject("proposals", proposals);
	}
	
	private void comprobarNumeroVotos() {
		List<Proposal> proposals = factory.getServicesFactory().getProposalService()
				.findByStatus(EstadosPropuesta.EnTramite);
		
		for (Proposal proposal : proposals) {
			if (proposal.getValoration() >= proposal.getMinVotes()) {
				proposal.setStatus(EstadosPropuesta.Aceptada);
			}
		}
	}
}
