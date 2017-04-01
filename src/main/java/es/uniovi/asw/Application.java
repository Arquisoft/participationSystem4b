package es.uniovi.asw;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {
			try {
				System.out.println("RUN");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		};
	}

}
