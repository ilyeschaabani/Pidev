package tn.esprit.pidevbackend.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidevbackend.Entity.Evaluation;

import java.util.Optional;

@Repository
public interface EvaluationRepository extends MongoRepository<Evaluation, String> {
    Optional<Evaluation> findFirstByIdEvaluation(String idEvaluation);
    Evaluation findEvaluationByIdEvaluation(String idEvaluation);

    Optional<Evaluation> findByIdEvaluation(String idEvaluation);
    // Utilisez le bon nom de propriété pour l'ID

  
}

