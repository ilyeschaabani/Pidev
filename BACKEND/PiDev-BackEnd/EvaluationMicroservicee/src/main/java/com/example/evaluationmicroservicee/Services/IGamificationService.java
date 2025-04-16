package com.example.evaluationmicroservicee.Services;


import com.example.evaluationmicroservicee.Entity.AttemptedQuizRecords;
import com.example.evaluationmicroservicee.Entity.Gamification;
import com.example.evaluationmicroservicee.Entity.User;

import java.util.List;

public interface IGamificationService {
    void processQuizResults(String idUser, AttemptedQuizRecords quizRecord);

    // Méthode pour obtenir les points en fonction du score
    int calculatePoints(double scorePercentage);

    // Méthode pour vérifier si l'utilisateur doit monter de niveau

    // Méthode pour vérifier et attribuer un badge en fonction du score
    String getBadgeForScore(double scorePercentage);

    // Méthode pour vérifier si l'utilisateur doit monter de niveau
    public User checkLevelUp(User user);
    // Méthode pour vérifier les badges de l'utilisateur
    void checkBadges(User user);

    // Méthode pour créer un enregistrement de gamification
    Gamification createGamificationRecord(User user, AttemptedQuizRecords quizRecord, int pointsEarned, double scorePercentage);

    Gamification getUserGamification(String idUser);

    List<String> getUserBadges(String idUser);
}
