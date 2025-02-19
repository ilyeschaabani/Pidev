package tn.esprit.pidevbackend.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import tn.esprit.pidevbackend.Entity.Projet;

public interface ProjetRepository extends MongoRepository<Projet, String> {
}
