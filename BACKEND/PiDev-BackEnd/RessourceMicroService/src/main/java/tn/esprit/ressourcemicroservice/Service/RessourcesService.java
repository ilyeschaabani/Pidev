package tn.esprit.ressourcemicroservice.Service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.ressourcemicroservice.Entity.Ressources;
import tn.esprit.ressourcemicroservice.Repository.RessourcesRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RessourcesService {

    private final RessourcesRepository ressourcesRepository;

    // Injection via constructeur (meilleure pratique)
    public RessourcesService(RessourcesRepository ressourcesRepository) {
        this.ressourcesRepository = ressourcesRepository;
    }

    // Créer une ressource
    public Ressources createRessource(Ressources ressource) {
        return ressourcesRepository.save(ressource);
    }

    // Récupérer toutes les ressources
    public List<Ressources> getAllRessources() {
        return ressourcesRepository.findAll();
    }

    // Récupérer une ressource par ID
    public Ressources getRessourceById(String id) {
        return ressourcesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ressource non trouvée avec l'ID : " + id));
    }

    // Mettre à jour une ressource
    public Ressources updateRessource(String id, Ressources updatedRessource) {
        return ressourcesRepository.findById(id).map(ressource -> {
            ressource.setTitre(updatedRessource.getTitre());
            ressource.setDescription(updatedRessource.getDescription());
            ressource.setType(updatedRessource.getType());
            ressource.setDate(updatedRessource.getDate());
            ressource.setCategory(updatedRessource.getCategory());

            // Use an if statement instead of the ternary operator for setting the file name
            if (updatedRessource.getFileName() != null) {
                ressource.setFileName(updatedRessource.getFileName());
            }

            return ressourcesRepository.save(ressource);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ressource non trouvée avec l'ID : " + id));
    }

    // Supprimer une ressource
    public void deleteRessource(String id) {
        if (!ressourcesRepository.existsById(id)) {
            System.out.println("NOT FOUND ! ");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ressource non trouvée avec l'ID : " + id);
        }
        ressourcesRepository.deleteById(id);
    }
}
