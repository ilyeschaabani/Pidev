package tn.esprit.pidevbackend.Services;

import org.springframework.stereotype.Service;
import tn.esprit.pidevbackend.Entity.Projet;
import tn.esprit.pidevbackend.Repositories.ProjetRepository;

import java.util.List;

@Service
public class ProjetServices {

    private final ProjetRepository projetRepository;

    // Injection de dépendance via constructeur (meilleure pratique)
    public ProjetServices(ProjetRepository projetRepository) {
        this.projetRepository = projetRepository;
    }

    // Ajouter un projet
    public Projet addProjet(Projet projet) {
        return projetRepository.save(projet);
    }

    // Mettre à jour un projet
    public Projet updateProjet(String id, Projet newProjet) {
        return projetRepository.findById(id)
                .map(existingProjet -> {
                    existingProjet.setTitre(newProjet.getTitre());
                    existingProjet.setDescription(newProjet.getDescription());
                    existingProjet.setPorteurProjet(newProjet.getPorteurProjet());
                    existingProjet.setEncadrant(newProjet.getEncadrant());
                    existingProjet.setEspaceCollaboratif(newProjet.getEspaceCollaboratif());
                    existingProjet.setStatutProjet(newProjet.getStatutProjet());
                    return projetRepository.save(existingProjet);
                })
                .orElseThrow(() -> new RuntimeException("Projet non trouvé avec l'ID : " + id));
    }

    // Supprimer un projet
    public String deleteProjet(String id) {
        return projetRepository.findById(id)
                .map(projet -> {
                    projetRepository.deleteById(id);
                    return "Projet supprimé avec succès";
                })
                .orElseThrow(() -> new RuntimeException("Projet non trouvé avec l'ID : " + id));
    }

    // Récupérer tous les projets
    public List<Projet> getAllProjets() {
        return projetRepository.findAll();
    }

    // Récupérer un projet par ID
    public Projet getProjetById(String id) {
        return projetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Projet non trouvé avec l'ID : " + id));
    }
}
