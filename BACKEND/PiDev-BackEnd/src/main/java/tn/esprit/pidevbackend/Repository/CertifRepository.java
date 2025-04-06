package tn.esprit.pidevbackend.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidevbackend.Entity.Certif;
@Repository
public interface CertifRepository extends MongoRepository<Certif, String> {

}
