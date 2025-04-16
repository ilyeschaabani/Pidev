package tn.esprit.paniermicroservice.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.paniermicroservice.Services.PanierService;
import tn.esprit.paniermicroservice.Entities.Panier;
import tn.esprit.paniermicroservice.dto.FormationDTO;

@RestController
@RequestMapping("/api/panier")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PanierController {

    private final PanierService panierService;

    @GetMapping("/{userId}")
    public Panier getPanier(@PathVariable Long userId) {
        return panierService.getOrCreatePanier(userId);
    }

    @PostMapping("/{userId}/add/{formationId}")
    public Panier addFormationById(@PathVariable Long userId, @PathVariable String formationId) {
        return panierService.addFormationById(userId, formationId);
    }


    @DeleteMapping("/{userId}/remove/{formationId}")
    public Panier removeFormation(@PathVariable Long userId, @PathVariable String formationId) {
        return panierService.removeFormation(userId, formationId);
    }

    @DeleteMapping("/{userId}/clear")
    public void clearPanier(@PathVariable Long userId) {
        panierService.clearPanier(userId);
    }
}
