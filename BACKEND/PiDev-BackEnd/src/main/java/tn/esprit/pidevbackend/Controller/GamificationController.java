package tn.esprit.pidevbackend.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidevbackend.Entity.Gamification;
import tn.esprit.pidevbackend.Services.GamificationService;

import java.util.List;

@RestController
@RequestMapping("/api/gamification")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class GamificationController {
    @Autowired
    private  GamificationService gamificationService;
    @PostMapping
    public Gamification addGamification(@RequestBody Gamification gamification) {
        return gamificationService.addGamification(gamification);
    }

    @PutMapping("/{id}")
    public Gamification updateGamification(@PathVariable String id, @RequestBody Gamification gamification) {
        return gamificationService.updateGamification(id, gamification);
    }

    @DeleteMapping("/{id}")
    public void deleteGamification(@PathVariable String id) {
        gamificationService.deleteGamification(id);
    }

    @GetMapping("/{id}")
    public Gamification getGamificationById(@PathVariable String id) {
        return gamificationService.getGamificationById(id);
    }

    @GetMapping
    public List<Gamification> getAllGamifications() {
        return gamificationService.getAllGamifications();
    }

}
