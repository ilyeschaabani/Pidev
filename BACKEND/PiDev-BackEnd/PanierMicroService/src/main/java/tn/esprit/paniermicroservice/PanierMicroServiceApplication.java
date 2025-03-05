package tn.esprit.paniermicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PanierMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PanierMicroServiceApplication.class, args);
	}

}
