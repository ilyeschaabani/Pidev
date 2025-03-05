package tn.esprit.paniermicroservice.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import tn.esprit.paniermicroservice.Entities.Panier;

public interface PanierRepository extends MongoRepository<Panier,Long> {
}
