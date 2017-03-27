package es.uniovi.asw;

import es.uniovi.asw.business.CitizenService;
import es.uniovi.asw.conf.Factories;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.model.Comentario;
import es.uniovi.asw.model.Propuesta;
import es.uniovi.asw.util.Printer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {
	@Autowired
	private Factories factories;

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Bean
	
	public CommandLineRunner demo() {
		return (args) -> {
			try {
				CitizenService servicio = factories.getServicesFactory()
						.getCitizenService();
				Citizen citizen = servicio.findByDni("21313782G");
				Propuesta propuesta = factories.getServicesFactory()
						.getPropuestaService().findById(1);
				Comentario comentario = new Comentario(citizen, propuesta,
						"A ver si funciona");
				factories.getServicesFactory().getComentarioService()
						.save(comentario);
			} catch (Exception e1) {
				e1.printStackTrace();
				new Printer().printCitizenException(e1);
			}
		};
	}
}
