package tn.esprit.pidevbackend.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import tn.esprit.pidevbackend.Entity.AccompagnementPFE;

public interface AccompagnementPFERepository extends MongoRepository<AccompagnementPFE, String> {
}
