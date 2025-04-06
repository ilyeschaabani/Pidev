package tn.esprit.pidevbackend.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidevbackend.Entity.Gamification;
import tn.esprit.pidevbackend.Repository.GamificationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GamificationService implements IGamificationService {
    @Autowired
    private  GamificationRepository gamificationRepository;

    @Override
    public Gamification addGamification(Gamification gamification) {
        return gamificationRepository.save(gamification);
    }

    @Override
    public Gamification updateGamification(String id, Gamification gamification) {
        if (gamificationRepository.existsById(id)) {
            gamification.setIdGamification(id);
            return gamificationRepository.save(gamification);
        } else {
            throw new RuntimeException("Gamification not found");
        }
    }

    @Override
    public void deleteGamification(String id) {
        gamificationRepository.deleteById(id);
    }

    @Override
    public Gamification getGamificationById(String id) {
        return gamificationRepository.findById(id).orElseThrow(() -> new RuntimeException("Gamification not found"));
    }
    @Override
    public List<Gamification> getAllGamifications() {
        return gamificationRepository.findAll();
    }

}

