package tn.esprit.paniermicroservice.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tn.esprit.paniermicroservice.Repositories.PanierRepository;
import tn.esprit.paniermicroservice.Entities.Panier;
import tn.esprit.paniermicroservice.dto.FormationDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PanierService {

    private final PanierRepository panierRepository;

    public Panier getOrCreatePanier(Long userId) {
        return panierRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Panier newPanier = new Panier(userId);
                    return panierRepository.save(newPanier);
                });
    }

    public Panier addFormationToPanier(Long userId, FormationDTO formation) {
        Panier panier = getOrCreatePanier(userId);
        panier.getFormations().add(formation);
        panier.setTotal(panier.getTotal() + formation.getPrix());
        return panierRepository.save(panier);
    }

    public Panier removeFormation(Long userId, String formationId) {
        Panier panier = getOrCreatePanier(userId);
        List<FormationDTO> updatedFormations = panier.getFormations().stream()
                .filter(f -> f.getIdFormation() != null && !f.getIdFormation().equals(formationId))
                .toList();
        double updatedTotal = updatedFormations.stream().mapToDouble(FormationDTO::getPrix).sum();
        panier.setFormations(updatedFormations);
        panier.setTotal(updatedTotal);
        return panierRepository.save(panier);
    }

    public void clearPanier(Long userId) {
        Panier panier = getOrCreatePanier(userId);
        panier.setFormations(List.of());
        panier.setTotal(0.0);
        panierRepository.save(panier);
    }

    @Autowired
    private RestTemplate restTemplate;

    private final String FORMATION_SERVICE_URL = "http://localhost:8082/api/formations"; // adjust if needed
    public Panier addFormationById(Long userId, String formationId) {
        // Call the Formation microservice to get the formation by ID
        FormationDTO formation = restTemplate.getForObject(
                FORMATION_SERVICE_URL + "/get/" + formationId,
                FormationDTO.class
        );

        if (formation == null) {
            throw new RuntimeException("Formation not found!");
        }

        // Reuse existing logic
        return addFormationToPanier(userId, formation);
    }

}
