package tn.esprit.projectmicroservice.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tn.esprit.projectmicroservice.Entity.Projet;
public interface ProjetRepository extends MongoRepository<Projet, String> {
}
