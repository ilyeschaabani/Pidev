package tn.esprit.authenticationmicroservice.Repository;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.authenticationmicroservice.Entity.Enum.Role;
import tn.esprit.authenticationmicroservice.Entity.User;


@Repository
public interface UserRepository extends MongoRepository<User, String> { // Use String as the ID type for MongoDB
    Optional<User> findByEmail(String email);
    User findByRole(Role role);
}
