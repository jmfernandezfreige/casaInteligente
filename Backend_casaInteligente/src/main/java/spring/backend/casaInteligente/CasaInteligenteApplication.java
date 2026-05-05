package spring.backend.casaInteligente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CasaInteligenteApplication {

	public static void main(String[] args) {
		SpringApplication.run(CasaInteligenteApplication.class, args);
	}

}
