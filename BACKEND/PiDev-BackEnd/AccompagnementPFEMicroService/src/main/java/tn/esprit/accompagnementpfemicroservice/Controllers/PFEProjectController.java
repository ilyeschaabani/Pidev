package tn.esprit.accompagnementpfemicroservice.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.accompagnementpfemicroservice.Entities.*;
import tn.esprit.accompagnementpfemicroservice.Repositories.PFEProjectRepository;
import tn.esprit.accompagnementpfemicroservice.Repositories.UserRepository;
import tn.esprit.accompagnementpfemicroservice.Services.GeminiService;
import tn.esprit.accompagnementpfemicroservice.Services.PFEProjectService;
import tn.esprit.accompagnementpfemicroservice.Services.ResourceNotFoundException;

import java.nio.file.AccessDeniedException;
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
    @GetMapping
    public List<PFEProject> getAllProjects() {
        return projectService.getAllProjects();
    }

    @PostMapping
    public PFEProject createProject(@RequestBody PFEProject project) {
        return projectService.createProject(project);
    }

    @PutMapping("/{id}/stage")
    public PFEProject updateProjectStage(@PathVariable String id, @RequestBody ProjectStage stage) {
        return projectService.updateProjectStage(id, stage);
    }



    // 🔸 Ajouter un document (Étudiant uniquement)
    @PostMapping("/{id}/document")
    public PFEProject addDocument(@PathVariable String id,
                                  @RequestBody DocumentFile document,
                                  @RequestParam String currentUsername) {
        return projectService.addDocumentToProject(id, document, currentUsername);
    }

    // 🔸 Évaluer le projet (Mentor uniquement)
    @PostMapping("/{id}/evaluate")
    public PFEProject evaluateProject(@PathVariable String id,
                                      @RequestBody Evaluation evaluation,
                                      @RequestParam String currentUsername) throws AccessDeniedException {
        return projectService.evaluateProject(id, evaluation, currentUsername);
    }

    @PostMapping("/{projectId}/comment")
    public ResponseEntity<?> addCommentToProject(
            @PathVariable String projectId,
            @RequestParam String currentUsername,
            @RequestBody Comment comment) {

        try {
            PFEProject updatedProject = projectService.addCommentToProject(projectId, comment, currentUsername);
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