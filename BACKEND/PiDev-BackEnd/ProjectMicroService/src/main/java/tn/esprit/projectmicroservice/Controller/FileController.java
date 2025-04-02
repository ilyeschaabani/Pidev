package tn.esprit.projectmicroservice.Controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.projectmicroservice.Entity.ProjectFile;
import tn.esprit.projectmicroservice.Service.FileService;

import java.io.IOException;

// FileController.java
@RestController
@RequestMapping("/api/files")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
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
    
}