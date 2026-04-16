package tn.esprit.projectmicroservice.Controller;

import org.bson.types.ObjectId;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.projectmicroservice.Entity.ProjectFile;
import tn.esprit.projectmicroservice.Service.FileService;
import tn.esprit.projectmicroservice.Service.ProjetService;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

// FileController.java
@RestController
@RequestMapping("/api/files")
public class FileController {

    private final FileService fileService;
    private final ProjetService projetService; // Ajout

    public FileController(FileService fileService, ProjetService projetService) {
        this.fileService = fileService;
        this.projetService = projetService; // Injection du service

    }

    @PostMapping("/upload/{projectId}")
    public ResponseEntity<?> uploadFile(
            @PathVariable String projectId,
            @RequestParam("file") MultipartFile file) {

        // Logs de débogage
        System.out.println("Tentative d'upload pour le projet: " + projectId);
        System.out.println("Fichier reçu: " + (file.isEmpty() ? "Vide" : file.getOriginalFilename()));

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Aucun fichier sélectionné");
        }

        try {
            ProjectFile savedFile = fileService.saveFile(file, projectId);
            return ResponseEntity.ok(savedFile);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Erreur de traitement du fichier");
        }
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String id) {
        ProjectFile file = fileService.getFile(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                .contentType(MediaType.parseMediaType(file.getFileType()))
                .body(file.getContent());
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<?> getProjectFiles(@PathVariable String projectId) {
        try {
            // Vérifier si l'ID est un ObjectId MongoDB valide
            if (!ObjectId.isValid(projectId)) {
                return ResponseEntity.badRequest().body("ID de projet invalide");
            }

            List<ProjectFile> files = fileService.findByProjectId(projectId);

            if (files.isEmpty()) {
                return ResponseEntity.ok().body(Collections.emptyList());
            }

            return ResponseEntity.ok(files);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erreur serveur");
        }
    }
}