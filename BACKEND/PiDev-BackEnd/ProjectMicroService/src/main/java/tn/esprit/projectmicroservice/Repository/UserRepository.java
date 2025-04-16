package tn.esprit.projectmicroservice.Repository;

import tn.esprit.projectmicroservice.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findById(String id); // Find a user by ID

        List<User> findByRole(String role);

}
