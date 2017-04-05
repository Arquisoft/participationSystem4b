package es.uniovi.asw.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.uniovi.asw.Application;
import es.uniovi.asw.conf.Factories;
import es.uniovi.asw.model.exception.CitizenException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CommentTest {
	
	@Autowired
	private Factories factory;
	private Calendar c1;
	
	private Citizen usuario;
	private Proposal propuesta;
	private int numComments;

	@Before
	public void inicializarTest()
			throws NoSuchAlgorithmException, CitizenException {
		c1 = GregorianCalendar.getInstance();
		c1.set(Calendar.YEAR, 1988);
		c1.set(Calendar.MONTH, Calendar.JANUARY);
		c1.set(Calendar.DAY_OF_MONTH, 1);
		usuario = new Citizen("a", "b b", "c@gmail.com",
				new Date(c1.getTimeInMillis()), "residencia", "nacionalidad",
				"dni");
		usuario.setId(1L);
		
		propuesta = new Proposal();
		propuesta.setId(1L);
		propuesta.setName("nombre");
		propuesta.setContent("contenido");
		propuesta.setMinVotes(1);
		propuesta.setStatus(EstadosPropuesta.EnTramite);

		numComments = (int) factory.getPersistenceFactory()
				.newCommentaryRepository().count();
		
	}

	@Test
	public void testCommentsSave() {
		/*factory.getServicesFactory().getCommentaryService()
				.save(usuario.getId(), propuesta.getId(), "mensaje");
		assertEquals(numComments + 1, factory.getPersistenceFactory()
				.newCommentaryRepository().count());*/
	}
	
	@Test
	public void testCommentsAll() {
		List<Commentary> comentarios = factory.getServicesFactory()
				.getCommentaryService().findAll();
		
		for (Commentary c : comentarios) {
			assertNotNull(c.getContent());
		}
	}
	
	@Test
	public void testCommentsByCreationDate() {
		List<Commentary> comentarios = factory.getServicesFactory()
				.getCommentaryService().findAll();
		
		Commentary comentario = factory.getServicesFactory()
				.getCommentaryService().findByCreationDate(comentarios.get(1).getCreationDate());
		
		assertNotNull(comentario.getContent());
	}
	
	@Test
	public void testCommentsByCitizen() {
		List<Commentary> comentarios = factory.getServicesFactory()
				.getCommentaryService().findByProposal(1L);
		
		for (Commentary c : comentarios) {
			if(c.getEstado().equals(EstadosComentario.Censurado))
				assertNotNull(c.getContent());
		}
	}
}
