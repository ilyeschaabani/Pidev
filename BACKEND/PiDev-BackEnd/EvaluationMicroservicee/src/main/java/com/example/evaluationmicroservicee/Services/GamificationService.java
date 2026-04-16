package com.example.evaluationmicroservicee.Services;

import com.example.evaluationmicroservicee.Entity.AttemptedQuizRecords;
import com.example.evaluationmicroservicee.Entity.Enumeration.Defi;
import com.example.evaluationmicroservicee.Entity.Gamification;
import com.example.evaluationmicroservicee.Entity.User;
import com.example.evaluationmicroservicee.Repository.EvaluationRepository;
import com.example.evaluationmicroservicee.Repository.GamificationRepository;
import com.example.evaluationmicroservicee.Repository.RecordRepository;
import com.example.evaluationmicroservicee.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GamificationService implements IGamificationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GamificationRepository gamificationRepository;

    @Autowired
    private RecordRepository attemptedQuizRecordsRepository;

    @Autowired
    private EvaluationRepository evaluationRepository;

    // Méthode principale pour traiter les résultats du quiz
    @Override
    public void processQuizResults(String idUser, AttemptedQuizRecords quizRecord) {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Calcul du score en fonction de la performance
        double scorePercentage = (quizRecord.getObtainedMarks() / (double) quizRecord.getEvaluation().getTotalMarks()) * 100;
        int pointsEarned = calculatePoints(scorePercentage);

        // Mise à jour des données de gamification de l'utilisateur
        user.getQuizAttempts().add(quizRecord);
        user.setTotalPoints(user.getTotalPoints() + pointsEarned);

        // Vérifier si l'utilisateur doit monter de niveau
        checkLevelUp(user);

        // Vérifier les badges que l'utilisateur peut obtenir
        checkBadges(user);

        // Créer un enregistrement de gamification
        Gamification gamification = createGamificationRecord(user, quizRecord, pointsEarned, scorePercentage);
        gamificationRepository.save(gamification);

        // Ajouter l'enregistrement de gamification à l'utilisateur
        user.getGamifications().add(gamification);

        // Sauvegarder les modifications de l'utilisateur
        userRepository.save(user);
    }

    // Méthode pour créer un enregistrement de gamification
    @Override
    public Gamification createGamificationRecord(User user, AttemptedQuizRecords quizRecord, int pointsEarned, double scorePercentage) {
        return Gamification.builder()
                .pts(pointsEarned)
                .niveau(user.getLevel())
                .badge(getBadgeForScore(scorePercentage))
                .defi(scorePercentage >= 75 ? Defi.COMPLETED : Defi.IN_PROGRESS)
                .idEvaluation(quizRecord.getEvaluation())
                .build();
    }

    // Méthode pour calculer les points en fonction du pourcentage de score
    @Override
    public int calculatePoints(double scorePercentage) {
        if (scorePercentage >= 90) {
            return 100;
        } else if (scorePercentage >= 75) {
            return 80;
        } else if (scorePercentage >= 50) {
            return 50;
        } else {
            return 20;
        }
    }

    // Méthode pour vérifier si l'utilisateur doit monter de niveau
    @Override
    public User checkLevelUp(User user) {
        if (user.getTotalPoints() >= 1000) {
            user.setLevel(Integer.parseInt(String.valueOf(user.getLevel() + 1)));
        }
        return user;
    }

    // Méthode pour vérifier et attribuer un badge en fonction du score
    @Override
    public void checkBadges(User user) {
        // Exemple de logique pour l'attribution de badges
        if (user.getTotalPoints() >= 500 && !user.getBadges().contains("Master")) {
            user.getBadges().add("Master");
        }
    }

    // Méthode pour obtenir le badge en fonction du score
    @Override
    public String getBadgeForScore(double scorePercentage) {
        if (scorePercentage >= 90) {
            return "Gold";
        } else if (scorePercentage >= 75) {
            return "Silver";
        } else if (scorePercentage >= 50) {
            return "Bronze";
        } else {
            return "Beginner";
        }
    }

    // Méthode pour récupérer les informations de gamification d'un utilisateur
    @Override
    public Gamification getUserGamification(String idUser) {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user.getGamifications().stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No gamification record found for this user"));
    }

    // Méthode pour récupérer les badges d'un utilisateur
    @Override
    public List<String> getUserBadges(String idUser) {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user.getBadges();
    }


}
