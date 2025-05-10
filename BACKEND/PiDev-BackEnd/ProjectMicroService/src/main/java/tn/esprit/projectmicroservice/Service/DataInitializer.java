package tn.esprit.projectmicroservice.Service;

import tn.esprit.projectmicroservice.Entity.User;
import tn.esprit.projectmicroservice.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    public DataInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Check if users are already in the database
        if (userRepository.count() == 0) {
            // Add static users
            User admin = User.builder()
                    .id("admin123") // unique ID
                    .username("admin") // username
                    .role("ADMIN") // role
                    .build();

            User encadrant = User.builder()
                    .id("encadrant123") // unique ID
                    .username("encadrant") // username
                    .role("ENCADRANT") // role
                    .build();

            User etudiant = User.builder()
                    .id("etudiant123") // unique ID
                    .username("etudiant") // username
                    .role("ETUDIANT") // role
                    .build();

            // Save users to the database
            userRepository.save(admin);
            userRepository.save(encadrant);
            userRepository.save(etudiant);

            System.out.println("Static users added to the database.");
        }
    }
}