package tn.esprit.authenticationmicroservice.Repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import tn.esprit.authenticationmicroservice.Entity.ForgotPassword;
import tn.esprit.authenticationmicroservice.Entity.User;

import java.util.Optional;

public interface ForgotPasswordRepository extends MongoRepository<ForgotPassword, String> {
    Optional<ForgotPassword> findByOtpAndUser(Integer otp, User user);

}
