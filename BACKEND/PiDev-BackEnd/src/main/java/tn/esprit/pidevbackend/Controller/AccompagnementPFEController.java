package tn.esprit.pidevbackend.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidevbackend.Entity.AccompagnementPFE;
import tn.esprit.pidevbackend.Services.AccompagnementPFEService;

import java.util.List;

@RestController
@RequestMapping("/api/accompagnementsPFE")
@CrossOrigin(origins = "*") // Permet les requêtes depuis n'importe quel frontend
public class AccompagnementPFEController {

    private final AccompagnementPFEService accompagnementPFEService;

    public AccompagnementPFEController(AccompagnementPFEService accompagnementPFEService) {
        this.accompagnementPFEService = accompagnementPFEService;
    }

    // Ajouter un accompagnement
    @PostMapping
    public ResponseEntity<AccompagnementPFE> addAccompagnement(@RequestBody AccompagnementPFE accompagnement) {
        return ResponseEntity.ok(accompagnementPFEService.addAccompagnement(accompagnement));
    }

    // Mettre à jour un accompagnement
    @PutMapping("/{id}")
    public ResponseEntity<AccompagnementPFE> updateAccompagnement(
            @PathVariable String id, @RequestBody AccompagnementPFE newAccompagnement) {
        return ResponseEntity.ok(accompagnementPFEService.updateAccompagnement(id, newAccompagnement));
    }

    // Supprimer un accompagnement
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccompagnement(@PathVariable String id) {
        return ResponseEntity.ok(accompagnementPFEService.deleteAccompagnement(id));
    }

    // Récupérer tous les accompagnements
    @GetMapping
    public ResponseEntity<List<AccompagnementPFE>> getAllAccompagnements() {
        return ResponseEntity.ok(accompagnementPFEService.getAllAccompagnements());
    }

    // Récupérer un accompagnement par ID
    @GetMapping("/{id}")
    public ResponseEntity<AccompagnementPFE> getAccompagnementById(@PathVariable String id) {
        return ResponseEntity.ok(accompagnementPFEService.getAccompagnementById(id));
    }
}
