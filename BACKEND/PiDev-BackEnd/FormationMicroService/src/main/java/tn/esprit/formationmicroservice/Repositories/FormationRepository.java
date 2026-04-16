package tn.esprit.formationmicroservice.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import tn.esprit.formationmicroservice.Entities.Formation;

public interface FormationRepository extends MongoRepository<Formation, String> {
}
