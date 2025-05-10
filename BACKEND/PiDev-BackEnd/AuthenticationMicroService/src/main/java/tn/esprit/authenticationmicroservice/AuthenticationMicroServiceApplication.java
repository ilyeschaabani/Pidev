package tn.esprit.authenticationmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication

public class AuthenticationMicroServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthenticationMicroServiceApplication.class, args);
    }

}
