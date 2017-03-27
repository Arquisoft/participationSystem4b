package es.uniovi.asw;

import es.uniovi.asw.conf.Factories;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.model.Comentario;
import es.uniovi.asw.model.Propuesta;
import es.uniovi.asw.util.Printer;

import java.util.Date;

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
				Citizen citizen = new Citizen("Prueba1", "addas", "",
						new Date(2000), "", "", "");
				// factories.getServicesFactory().getCitizenService()
				// .save(citizen);
				Propuesta propuesta = new Propuesta("Prueba2", "", 25);
				// factories.getServicesFactory().getPropuestaService()
				// .save(propuesta);
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
