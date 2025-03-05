package tn.esprit.projectmicroservice.Service;

import org.springframework.stereotype.Service;
import tn.esprit.projectmicroservice.Entity.Projet;
import tn.esprit.projectmicroservice.Repository.ProjetRepository;
import tn.esprit.projectmicroservice.Entity.Enumeration.StatutProjet;

import java.util.List;

@Service
public class ProjetService {

    private final ProjetRepository projetRepository;

    public ProjetService(ProjetRepository projetRepository) {
        this.projetRepository = projetRepository;
    }

    public Projet addProjet(Projet projet) {
        // Ajoute directement le projet sans gestion des fichiers
        return projetRepository.save(projet);
    }

    public Projet updateProjet(String id, Projet newProjet) {
        return projetRepository.findById(id)
                .map(existingProjet -> {
                    existingProjet.setTitre(newProjet.getTitre());
                    existingProjet.setDescription(newProjet.getDescription());
                    existingProjet.setPorteurProjet(newProjet.getPorteurProjet());
                    existingProjet.setEncadrant(newProjet.getEncadrant());
                    existingProjet.setEspaceCollaboratif(newProjet.getEspaceCollaboratif());
                    existingProjet.setStatutProjet(newProjet.getStatutProjet());
                    existingProjet.setEmail(newProjet.getEmail());
                    existingProjet.setTelephone(newProjet.getTelephone());
                    existingProjet.setTechnologies(newProjet.getTechnologies());
                    existingProjet.setObjectifs(newProjet.getObjectifs());
                    existingProjet.setBenefices(newProjet.getBenefices());
                    existingProjet.setRejectionMotif(newProjet.getRejectionMotif()); // Ajout du motif de rejet
                    return projetRepository.save(existingProjet);
                })
                .orElseThrow(() -> new RuntimeException("Projet non trouvé avec l'ID : " + id));
    }

    public String deleteProjet(String id) {
        return projetRepository.findById(id)
                .map(projet -> {
                    projetRepository.deleteById(id);
                    return "Projet supprimé avec succès";
                })
                .orElseThrow(() -> new RuntimeException("Projet non trouvé avec l'ID : " + id));
    }

    public List<Projet> getAllProjets() {
        return projetRepository.findAll();
    }

    public Projet getProjetById(String id) {
        return projetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Projet non trouvé avec l'ID : " + id));
    }

    public Projet validateOrRejectProjet(String id, boolean isValid, String rejectionMotif) {
        return projetRepository.findById(id)
                .map(projet -> {
                    if (isValid) {
                        projet.setStatutProjet(StatutProjet.EN_COURS);
                        projet.setRejectionMotif(null); // Effacer le motif de rejet si accepté
                    } else {
                        projet.setStatutProjet(StatutProjet.EN_ATTENTE);
                        projet.setRejectionMotif(rejectionMotif); // Stocker le motif de rejet
                    }
                    return projetRepository.save(projet);
                })
                .orElseThrow(() -> new RuntimeException("Projet non trouvé avec l'ID : " + id));
    }
    public Projet assignEncadrant(String id, String encadrant) {
        return projetRepository.findById(id)
                .map(projet -> {
                    if (projet.getStatutProjet() == StatutProjet.EN_COURS) {
                        projet.setEncadrant(encadrant);  // Assign the "encadrant"
                        return projetRepository.save(projet);
                    } else {
                        throw new RuntimeException("Le projet doit être validé avant d'attribuer un encadrant.");
                    }
                })
                .orElseThrow(() -> new RuntimeException("Projet non trouvé avec l'ID : " + id));
    }

}
