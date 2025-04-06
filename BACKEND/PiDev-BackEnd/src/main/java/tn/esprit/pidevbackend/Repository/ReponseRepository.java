package tn.esprit.pidevbackend.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidevbackend.Entity.Reponse;
@Repository
public interface ReponseRepository  extends MongoRepository<Reponse, String> {
}
