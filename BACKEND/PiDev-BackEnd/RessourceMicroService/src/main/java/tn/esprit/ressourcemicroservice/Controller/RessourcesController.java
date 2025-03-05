package tn.esprit.ressourcemicroservice.Controller;

 import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.ressourcemicroservice.Entity.Ressources;
import tn.esprit.ressourcemicroservice.Service.RessourcesService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
;

@CrossOrigin(origins = "http://localhost:4200") // Autorise les requêtes depuis Angular
@RestController
@RequestMapping("/api/ressources")
public class RessourcesController {

    private final RessourcesService ressourcesService;

    // Injection par constructeur (meilleure pratique)
    public RessourcesController(RessourcesService ressourcesService) {
        this.ressourcesService = ressourcesService;
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

}
