package es.uniovi.asw;

import es.uniovi.asw.conf.Factories;
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

			} catch (Exception e1) {
				e1.printStackTrace();
				new Printer().printCitizenException(e1);
			}
		};
	}
}
