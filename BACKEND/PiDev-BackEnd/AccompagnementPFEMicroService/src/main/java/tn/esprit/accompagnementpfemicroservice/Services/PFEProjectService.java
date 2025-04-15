package tn.esprit.accompagnementpfemicroservice.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.accompagnementpfemicroservice.Entities.*;
import tn.esprit.accompagnementpfemicroservice.Repositories.PFEProjectRepository;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PFEProjectService {
    @Autowired
    private PFEProjectRepository projectRepository;

    public List<PFEProject> getAllProjects() {
        return projectRepository.findAll();
    }
    public PFEProject createProject(PFEProject project) {
        System.out.println("Saving project: " + project.getTitle());
        return projectRepository.save(project);
    }



    public Optional<PFEProject> getProjectById(String id) {
        return projectRepository.findById(id);
    }

    public PFEProject updateProjectStage(String id, ProjectStage stage) {
        PFEProject project = projectRepository.findById(id).orElseThrow();
        project.setStage(stage);
        return projectRepository.save(project);
    }

    public PFEProject addCommentToProject(String projectId, Comment comment, String currentUsername) throws AccessDeniedException {
        PFEProject project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Projet non trouvé"));

        if (project.getMentorId() == null || !project.getMentorId().equals(currentUsername)) {
            throw new AccessDeniedException("Ce projet n’est pas sous votre supervision");
        }

        if (project.getComments() == null) {
            project.setComments(new ArrayList<>());
        }

        if (comment.getCreatedAt() == null) {
            comment.setCreatedAt(new Date());
        }

        project.getComments().add(comment);
        return projectRepository.save(project);
    }

    public PFEProject addDocumentToProject(String projectId, DocumentFile doc, String currentUsername) {
        PFEProject project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Projet non trouvé"));

        // Vérifie si l'étudiant est associé au projet en utilisant studentIds
        if (project.getStudentIds() == null || project.getStudentIds().isEmpty()) {
            throw new SecurityException("Le projet ne possède pas d'étudiant associé.");
        }

        // Si le currentUsername ne correspond pas à l'un des étudiants associés, refuse l'accès
        if (!project.getStudentIds().contains(currentUsername)) {
            throw new SecurityException("Vous ne pouvez pas modifier ce projet");
        }

        project.getDocuments().add(doc);
        return projectRepository.save(project);
    }



    public PFEProject evaluateProject(String id, Evaluation evaluation, String currentUsername) throws AccessDeniedException {
        PFEProject project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Projet non trouvé"));

        if (project.getMentorId() == null || !project.getMentorId().equals(currentUsername)) {
            throw new AccessDeniedException("Ce projet n’est pas sous votre supervision");
        }
        project.setEvaluation(evaluation);
        return projectRepository.save(project);
    }
}