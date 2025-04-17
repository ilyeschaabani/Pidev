package tn.esprit.accompagnementpfemicroservice.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.accompagnementpfemicroservice.Entities.*;
import tn.esprit.accompagnementpfemicroservice.Repositories.PFEProjectRepository;
import tn.esprit.accompagnementpfemicroservice.Repositories.UserRepository;
import tn.esprit.accompagnementpfemicroservice.Services.GeminiService;
import tn.esprit.accompagnementpfemicroservice.Services.PFEProjectService;
import tn.esprit.accompagnementpfemicroservice.Services.ResourceNotFoundException;

import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "http://localhost:4200") // autorise Angular
public class PFEProjectController {
    @Autowired
    private PFEProjectService projectService;
    @Autowired
    private GeminiService geminiService;

    @PostMapping("/generate-topics")
    public ResponseEntity<String> generatePfeTopics(@RequestBody String studentProfile) {
        String topics = geminiService.generatePfeTopics(studentProfile);
        return ResponseEntity.ok(topics);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PFEProject> getProjectById(@PathVariable String id) {
        return projectService.getProjectById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Projet non trouvé"));
    }


    @GetMapping
    public List<PFEProject> getAllProjects() {
        return projectService.getAllProjects();
    }

    @PostMapping
    public PFEProject createProject(@RequestBody PFEProject project) {
        return projectService.createProject(project);
    }
    // 🔄 Mettre à jour un projet complet
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProject(@PathVariable String id, @RequestBody PFEProject project) {
        try {
            PFEProject updated = projectService.updateProject(id, project);
            return ResponseEntity.ok(updated);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // ❌ Supprimer un projet
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable String id) {
        try {
            projectService.deleteProject(id);
            return ResponseEntity.ok("Projet supprimé avec succès.");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}/stage")
    public PFEProject updateProjectStage(@PathVariable String id, @RequestBody ProjectStage stage) {
        return projectService.updateProjectStage(id, stage);
    }



    // 🔸 Ajouter un document (Étudiant uniquement)
    // 🔸 Ajouter un document (Étudiant uniquement) : modification pour accepter un fichier PDF
    @PostMapping("/{id}/document")
    public ResponseEntity<?> addDocument(@PathVariable String id,
                                         @RequestParam("file") MultipartFile file) {
        try {
            // Vérifier que le fichier est un PDF
            if (!file.getContentType().equals("application/pdf")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le fichier doit être un PDF.");
            }

            // Sauvegarder le fichier et ajouter le document au projet
            PFEProject updatedProject = projectService.addDocumentToProject(id, file);
            return ResponseEntity.ok(updatedProject);
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur serveur : " + e.getMessage());
        }
    }

    // 🔸 Évaluer le projet (Mentor uniquement)
    @PostMapping("/{id}/evaluate")
    public PFEProject evaluateProject(@PathVariable String id,
                                      @RequestBody Evaluation evaluation) {
        return projectService.evaluateProject(id, evaluation);
    }

    @PostMapping("/{projectId}/comment")
    public ResponseEntity<?> addCommentToProject(
            @PathVariable String projectId,
            @RequestBody Comment comment) {

        try {
            PFEProject updatedProject = projectService.addCommentToProject(projectId, comment);
            return ResponseEntity.ok(updatedProject);
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur serveur : " + e.getMessage());
        }
    }
}