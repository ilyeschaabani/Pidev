package tn.esprit.pidevbackend.Services;

import tn.esprit.pidevbackend.Entity.Gamification;

import java.util.List;

public interface IGamificationService {
    Gamification addGamification(Gamification gamification);
    Gamification updateGamification(String id, Gamification gamification);
    void deleteGamification(String id);
    Gamification getGamificationById(String id);
    List<Gamification> getAllGamifications();
}
