package tn.esprit.projectmicroservice.Service;

import org.springframework.stereotype.Service;
import tn.esprit.projectmicroservice.Entity.Milestone;
import tn.esprit.projectmicroservice.Repository.MilestoneRepository;
import tn.esprit.projectmicroservice.Repository.ProjetRepository;

import java.util.List;

@Service
public class MilestoneService {

    private final MilestoneRepository milestoneRepository;
    private final ProjetRepository projetRepository;

    public MilestoneService(MilestoneRepository milestoneRepository, ProjetRepository projetRepository) {
        this.milestoneRepository = milestoneRepository;
        this.projetRepository = projetRepository;
    }

    // Ajouter la vérification de l'existence du projet
    public Milestone addMilestone(Milestone milestone) {
        if(!projetRepository.existsById(milestone.getProjectId())) {
            throw new RuntimeException("Project not found with id: " + milestone.getProjectId());
        }
        return milestoneRepository.save(milestone);
    }

    // Nouvelles méthodes pour update/delete
    public Milestone updateMilestone(String id, Milestone milestoneDetails) {
        Milestone milestone = milestoneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Milestone not found"));
        milestone.setTitle(milestoneDetails.getTitle());
        milestone.setDescription(milestoneDetails.getDescription());
        milestone.setDueDate(milestoneDetails.getDueDate());
        milestone.setCompleted(milestoneDetails.isCompleted());
        return milestoneRepository.save(milestone);
    }

    public void deleteMilestone(String id) {
        milestoneRepository.deleteById(id);
    }
    // Ajouter cette méthode manquante
    public List<Milestone> getMilestonesByProjectId(String projectId) {
        return milestoneRepository.findByProjectId(projectId);
    }

    // Ajouter une méthode pour récupérer un milestone par son ID
    public Milestone getMilestoneById(String id) {
        return milestoneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Milestone not found"));
    }
}