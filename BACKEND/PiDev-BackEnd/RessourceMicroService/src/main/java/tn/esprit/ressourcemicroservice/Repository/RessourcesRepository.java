package tn.esprit.ressourcemicroservice.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.ressourcemicroservice.Entity.Enumeration.TypeRessource;
import tn.esprit.ressourcemicroservice.Entity.Ressources;

import java.util.List;

@Repository
public interface RessourcesRepository extends MongoRepository<Ressources, String> {
    @Query("{$and: [ " +
            "{$or: [ " +
            "{'titre': {$regex: ?0, $options: 'i'}}, " +
            "{'description': {$regex: ?0, $options: 'i'}} " +
            "]}, " +
            "{'type': {$regex: ?1, $options: 'i'}} " +
            "]}")
    List<Ressources> searchByKeywordAndType(String keyword, String type);

    List<Ressources> findByType(TypeRessource type);

    List<Ressources> findByTitreContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String titre, String description);

}