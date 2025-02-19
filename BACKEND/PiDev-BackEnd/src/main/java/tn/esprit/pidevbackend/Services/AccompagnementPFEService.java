package tn.esprit.pidevbackend.Services;

import org.springframework.stereotype.Service;
import tn.esprit.pidevbackend.Entity.AccompagnementPFE;
import tn.esprit.pidevbackend.Repositories.AccompagnementPFERepository;

import java.util.List;

@Service
public class AccompagnementPFEService {

    private final AccompagnementPFERepository accompagnementPFERepository;

    public AccompagnementPFEService(AccompagnementPFERepository accompagnementPFERepository) {
        this.accompagnementPFERepository = accompagnementPFERepository;
    }

    // Ajouter un accompagnement PFE
    public AccompagnementPFE addAccompagnement(AccompagnementPFE accompagnement) {
        return accompagnementPFERepository.save(accompagnement);
    }

    // Mettre à jour un accompagnement PFE
    public AccompagnementPFE updateAccompagnement(String id, AccompagnementPFE newAccompagnement) {
        return accompagnementPFERepository.findById(id)
                .map(existingAccompagnement -> {
                    existingAccompagnement.setEtudiant(newAccompagnement.getEtudiant());
                    existingAccompagnement.setEncadrant(newAccompagnement.getEncadrant());
                    existingAccompagnement.setSujet(newAccompagnement.getSujet());
                    existingAccompagnement.setAvancement(newAccompagnement.getAvancement());
                    existingAccompagnement.setParticipantsPFE(newAccompagnement.getParticipantsPFE());
                    return accompagnementPFERepository.save(existingAccompagnement);
                })
                .orElseThrow(() -> new RuntimeException("Accompagnement non trouvé avec l'ID : " + id));
    }

    // Supprimer un accompagnement PFE
    public String deleteAccompagnement(String id) {
        return accompagnementPFERepository.findById(id)
                .map(accompagnement -> {
                    accompagnementPFERepository.deleteById(id);
                    return "Accompagnement supprimé avec succès";
                })
                .orElseThrow(() -> new RuntimeException("Accompagnement non trouvé avec l'ID : " + id));
    }

    // Récupérer tous les accompagnements PFE
    public List<AccompagnementPFE> getAllAccompagnements() {
        return accompagnementPFERepository.findAll();
    }

    // Récupérer un accompagnement PFE par ID
    public AccompagnementPFE getAccompagnementById(String id) {
        return accompagnementPFERepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Accompagnement non trouvé avec l'ID : " + id));
    }
}
