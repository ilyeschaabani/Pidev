package tn.esprit.paniermicroservice.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import tn.esprit.paniermicroservice.Entities.Panier;

import java.util.Optional;

public interface PanierRepository extends MongoRepository<Panier,String> {
    Optional<Panier> findByUserId(Long userId);
}
