package tn.esprit.pidevbackend.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidevbackend.Entity.Gamification;
@Repository
public interface GamificationRepository extends MongoRepository<Gamification, String> {
}
