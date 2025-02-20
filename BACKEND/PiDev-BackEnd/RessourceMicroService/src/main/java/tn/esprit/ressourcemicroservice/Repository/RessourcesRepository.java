package tn.esprit.ressourcemicroservice.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.ressourcemicroservice.Entity.Ressources;

import java.util.List;

@Repository
public interface RessourcesRepository extends MongoRepository<Ressources, String> {

    // Recherche par titre (ignorer la casse)
    List<Ressources> findByTitreIgnoreCase(String titre);

    // Recherche par type de ressource
    List<Ressources> findByType(String type);

    // Recherche par catégorie
    List<Ressources> findByCategory(String category);
}
