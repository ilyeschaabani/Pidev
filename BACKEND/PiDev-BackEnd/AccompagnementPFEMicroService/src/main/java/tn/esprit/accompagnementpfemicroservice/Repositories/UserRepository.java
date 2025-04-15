package tn.esprit.accompagnementpfemicroservice.Repositories;


import org.springframework.data.mongodb.repository.MongoRepository;
import tn.esprit.accompagnementpfemicroservice.Entities.Role;
import tn.esprit.accompagnementpfemicroservice.Entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findById(String id); // Find a user by ID

    List<User> findByRole(Role role);


}
