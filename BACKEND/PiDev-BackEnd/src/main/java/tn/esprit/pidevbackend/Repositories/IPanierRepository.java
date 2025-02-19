package tn.esprit.pidevbackend.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import tn.esprit.pidevbackend.Entity.Panier;

public interface IPanierRepository extends MongoRepository<Panier,String> {
}
