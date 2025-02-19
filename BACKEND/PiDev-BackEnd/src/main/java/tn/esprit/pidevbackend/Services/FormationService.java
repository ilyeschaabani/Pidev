package tn.esprit.pidevbackend.Services;

import org.springframework.stereotype.Service;
import tn.esprit.pidevbackend.Entity.Formation;
import tn.esprit.pidevbackend.Repositories.IFormationRepository;
import tn.esprit.pidevbackend.Repositories.IFormationRepository;

import java.util.List;

@Service
public class FormationService {

    private final IFormationRepository  formationRepository;

    // Injection de dépendance via constructeur (meilleure pratique)
    public FormationService(IFormationRepository formationRepository) {
        this.formationRepository = formationRepository;
    }

    // Ajouter une formation
    public Formation addFormation(Formation formation) {
        return formationRepository.save(formation);
    }

    // Mettre à jour une formation
    public Formation updateFormation(String id, Formation newFormation) {
        return formationRepository.findById(id)
                .map(existingFormation -> {
                    existingFormation.setImage(newFormation.getImage());
                    existingFormation.setTitreFormation(newFormation.getTitreFormation());
                    existingFormation.setDescription(newFormation.getDescription());
                    existingFormation.setDateDebut(newFormation.getDateDebut());
                    existingFormation.setDateFin(newFormation.getDateFin());
                    existingFormation.setCategorie(newFormation.getCategorie());
                    return formationRepository.save(existingFormation);
                })
                .orElseThrow(() -> new RuntimeException("Formation non trouvée avec l'ID : " + id));
    }

    // Supprimer une formation
    public String deleteFormation(String id) {
        return formationRepository.findById(id)
                .map(formation -> {
                    formationRepository.deleteById(id);
                    return "Formation supprimée avec succès";
                })
                .orElseThrow(() -> new RuntimeException("Formation non trouvée avec l'ID : " + id));
    }

    // Récupérer toutes les formations
    public List<Formation> getAllFormations() {
        return formationRepository.findAll();
    }

    // Récupérer une formation par ID
    public Formation getFormationById(String id) {
        return formationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Formation non trouvée avec l'ID : " + id));
    }
}