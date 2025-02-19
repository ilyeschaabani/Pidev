package tn.esprit.pidevbackend.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import tn.esprit.pidevbackend.Entity.Formation;

public interface IFormationRepository extends MongoRepository<Formation, String> {
}
