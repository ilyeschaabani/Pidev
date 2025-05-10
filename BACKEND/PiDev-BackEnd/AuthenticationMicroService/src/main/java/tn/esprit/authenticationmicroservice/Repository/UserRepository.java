package tn.esprit.authenticationmicroservice.Repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;
import tn.esprit.authenticationmicroservice.Entity.Enum.Role;
import tn.esprit.authenticationmicroservice.Entity.User;


@Repository
public interface UserRepository extends MongoRepository<User, String> { // Use String as the ID type for MongoDB
    Optional<User> findByEmail(String email);
    List<User> findByRole(String role);

    // Password update implementation for MongoDB
    @Query("{ 'email' : ?0 }")
    default void updatePassword(String email, String password) {
        User user = findByEmail(email).orElseThrow(
                () -> new RuntimeException("User not found with email: " + email)
        );
        user.setPassword(password);
        save(user);
    }


}
