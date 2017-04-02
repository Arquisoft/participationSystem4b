package es.uniovi.asw.model;

import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Random;

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
public class ProposalTest {

	@Autowired
	private Factories factory;
	
	private Proposal proposal;
	private int numProposals;
	
	@Before
	public void inicializeTest()
			throws NoSuchAlgorithmException, CitizenException {
		proposal = new Proposal("Test", "ProposalTest", 10);
		numProposals = (int) factory.getPersistenceFactory()
				.newProposalRepository().count();
	}
	
	@Test
	public void testProposalSave() {
		factory.getServicesFactory().getProposalService().save(proposal);
		assertEquals(numProposals + 1,
				factory.getPersistenceFactory().newProposalRepository().count());
	}
	
	@Test
	public void testProposalAll() {
		List<Proposal> proposals = factory.getServicesFactory()
				.getProposalService().findAll();
		
		for (Proposal p : proposals){
			assertNotNull(p.getName());
			assertNotNull(p.getContent());
			assertNotNull(p.getMinVotes());
			assertNotNull(p.getStatus());
		}
	}
	
	@Test
	public void testProposalFindById(){
		for (int i = 1; i < 10; i++) {
			int id = generarAleatorio(numProposals);
		Proposal p = factory.getServicesFactory()
				.getProposalService().findById(id);
		assertNotNull(p.getName());
		assertNotNull(p.getContent());
		assertNotNull(p.getMinVotes());
		assertNotNull(p.getStatus());
		
		}
	}
	
	@Test
	public void testProposalFindByStatus() {
		List<Proposal> proposals = factory.getServicesFactory()
				.getProposalService()
				.findByStatus(EstadosPropuesta.EnTramite);
		for (Proposal p : proposals) {
			assertNotNull(p.getName());
			assertNotNull(p.getContent());
			assertNotNull(p.getMinVotes());
			assertNotNull(p.getStatus());
		}

	}
	
	@Test
	public void testProposalFindByCitizen(){
		Citizen c = factory.getServicesFactory().getCitizenService()
				.findById(501L);
		Proposal p = factory.getServicesFactory()
				.getProposalService().findByCitizen(c.getDni());
		
		assertNotNull(p.getName());
		assertNotNull(p.getContent());
		assertNotNull(p.getMinVotes());
		assertNotNull(p.getStatus());
		
	}
	
	@Test
	public void testProposalUpdate(){
		Proposal p = factory.getServicesFactory()
				.getProposalService().findById(1L);
		String contenido = p.getContent();
		p.setContent("cambiado");	
		assertFalse(contenido.equals(p.getContent()));
		p.setContent(contenido);
		assertTrue(contenido.equals(p.getContent()));
	}
	
	@Test
	public void testProposalRefuse(){
		Proposal p = factory.getServicesFactory()
				.getProposalService()
				.findById(numProposals + 1);
		p.getStatus().equals(EstadosPropuesta.Rechazada);
		assertEquals(EstadosPropuesta.Rechazada,
				factory.getServicesFactory()
				.getProposalService()
				.findById(numProposals + 1)
				.getStatus());
	}
	
	private int generarAleatorio(int maximo) {
		Random random = new Random();
		return (int) (random.nextDouble() * maximo) + 1;
	}
}
