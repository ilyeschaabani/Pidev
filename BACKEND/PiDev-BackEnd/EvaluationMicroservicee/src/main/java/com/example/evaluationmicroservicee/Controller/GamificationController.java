package com.example.evaluationmicroservicee.Controller;

import com.example.evaluationmicroservicee.Entity.AttemptedQuizRecords;
import com.example.evaluationmicroservicee.Entity.Gamification;
import com.example.evaluationmicroservicee.Entity.User;
import com.example.evaluationmicroservicee.Services.GamificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/gamification")
@CrossOrigin(origins = "http://localhost:4200")
public class GamificationController {

    private final GamificationService gamificationService;

    public GamificationController(GamificationService gamificationService) {
        this.gamificationService = gamificationService;
    }

    @PostMapping("/process-quiz/{idUser}")
    public ResponseEntity<?> processQuizResults(
            @PathVariable String idUser,
            @RequestBody AttemptedQuizRecords quizRecord) {

        try {
            gamificationService.processQuizResults(idUser, quizRecord);
            return ResponseEntity.ok().body(Map.of(
                    "status", "success",
                    "message", "Quiz results processed successfully"
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "error",
                    "message", e.getMessage()
            ));
        }
    }

    @GetMapping("/user/{idUser}")
    public ResponseEntity<?> getUserGamification(@PathVariable String idUser) {
        try {
            Gamification gamification = gamificationService.getUserGamification(idUser);
            return ResponseEntity.ok(gamification);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "error",
                    "message", e.getMessage()
            ));
        }
    }

    @GetMapping("/badges/{userId}")
    public ResponseEntity<?> getUserBadges(@PathVariable String idUser) {
        try {
            List<String> badges = gamificationService.getUserBadges(idUser);
            return ResponseEntity.ok(badges);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "error",
                    "message", e.getMessage()
            ));
        }
    }

    @GetMapping("/level-up/{idUser}")
    public ResponseEntity<?> checkLevelUp(@PathVariable User User) {
        try {
            User user = gamificationService.checkLevelUp(User);
            return ResponseEntity.ok(Map.of(
                    "newLevel", user.getLevel(),
                    "totalPoints", user.getTotalPoints()
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "error",
                    "message", e.getMessage()
            ));
        }
    }


}