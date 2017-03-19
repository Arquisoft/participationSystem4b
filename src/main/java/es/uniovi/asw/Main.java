package es.uniovi.asw;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.persistence.CitizenRepository;
import es.uniovi.asw.util.Printer;

@SpringBootApplication
public class Main {
	@Autowired
	private CitizenRepository citizenRepository;

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {
			try {
				List<Citizen> citizens = citizenRepository.findAll();
				System.err.println(citizens.size());
			} catch (Exception e1) {
				new Printer().printCitizenException(e1);
			}
		};
	}
}
