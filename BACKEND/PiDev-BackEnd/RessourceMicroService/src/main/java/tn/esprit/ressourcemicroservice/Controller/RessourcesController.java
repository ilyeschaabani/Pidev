package tn.esprit.ressourcemicroservice.Controller;

 import org.springframework.http.HttpStatus;
 import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 import tn.esprit.ressourcemicroservice.Entity.Enumeration.TypeRessource;
 import tn.esprit.ressourcemicroservice.Entity.Ressources;
 import tn.esprit.ressourcemicroservice.Service.FileStorageService;
 import tn.esprit.ressourcemicroservice.Service.RessourcesService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

 import java.io.IOException;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 ;

@CrossOrigin(origins = "http://localhost:4200") // Autorise les requêtes depuis Angular
@RestController
@RequestMapping("/api/ressources")
public class RessourcesController {

    private final RessourcesService ressourcesService;
    private final FileStorageService fileStorageService;

    // Injection par constructeur (meilleure pratique)
    public RessourcesController(RessourcesService ressourcesService,FileStorageService fileStorageService) {
        this.ressourcesService = ressourcesService;
        this.fileStorageService=fileStorageService;

    }

    // Créer une ressource
    @PostMapping
    public ResponseEntity<Ressources> createRessource(@RequestBody Ressources ressource) {
        return ResponseEntity.ok(ressourcesService.createRessource(ressource));
    }

    // Récupérer toutes les ressources
    @GetMapping
    public ResponseEntity<List<Ressources>> getAllRessources() {
        return ResponseEntity.ok(ressourcesService.getAllRessources());
    }

    @GetMapping("/resume/{fileName}")

    public ResponseEntity<Map<String, Object>> getResume(@PathVariable String fileName) {
        try {
            // Assuming resumeDocument returns a String (summary of the document)
            String resume = fileStorageService.resumeDocument(fileName);

            // Create a Map for the successful response
            Map<String, Object> response = new HashMap<>();
            response.put("summary", resume);

            return ResponseEntity.ok().body(response);
        } catch (IOException e) {
            // Create a Map for error response
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Error processing file");

            // Return error response with INTERNAL_SERVER_ERROR status
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    // Récupérer une ressource par ID
    @GetMapping("/{id}")
    public ResponseEntity<Ressources> getRessourceById(@PathVariable String id) {
        return ResponseEntity.ok(ressourcesService.getRessourceById(id));
    }

    // Mettre à jour une ressource
    @PutMapping("/{id}")
    public ResponseEntity<Ressources> updateRessource(@PathVariable String id, @RequestBody Ressources updatedRessource) {
        return ResponseEntity.ok(ressourcesService.updateRessource(id, updatedRessource));
    }

    // Supprimer une ressource
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRessource(@PathVariable String id) {
        ressourcesService.deleteRessource(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/recherche")
    public ResponseEntity<?> rechercherParType(@RequestParam("type") String typeStr) {
        try {
            TypeRessource type = TypeRessource.valueOf(typeStr.toUpperCase());
            List<Ressources> ressources = ressourcesService.rechercherParType(type);
            return ResponseEntity.ok(ressources);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Type invalide : " + typeStr);
        }
    }

    // Recherche par mot-clé
    @GetMapping("/search")
    public ResponseEntity<List<Ressources>> searchRessources(@RequestParam("keyword") String keyword) {
        List<Ressources> resultats = ressourcesService.searchRessources(keyword);
        return ResponseEntity.ok(resultats);
    }

}
