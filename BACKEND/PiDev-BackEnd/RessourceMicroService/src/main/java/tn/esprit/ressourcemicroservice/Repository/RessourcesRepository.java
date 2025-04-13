package tn.esprit.ressourcemicroservice.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.ressourcemicroservice.Entity.Enumeration.TypeRessource;
import tn.esprit.ressourcemicroservice.Entity.Ressources;

import java.util.List;

@Repository
public interface RessourcesRepository extends MongoRepository<Ressources, String> {
    List<Ressources> findByTitreContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String titreKeyword, String descriptionKeyword);

    List<Ressources> findByType(TypeRessource type);
}

