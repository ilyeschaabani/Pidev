package tn.esprit.pidevbackend.Services;

import tn.esprit.pidevbackend.Entity.Evaluation;
import tn.esprit.pidevbackend.Entity.Question;
import tn.esprit.pidevbackend.Entity.Reponse;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IEvaluationService {
    //Créer une nouvelle évaluation
    Evaluation createEvaluation(Evaluation evaluation);

    //    // Lire toutes les évaluations
    List<Evaluation> getAllEvaluations();
    //
//    // Lire une évaluation par ID
//    Optional<Evaluation> getEvaluationById(String idEvaluation);
//
//    // Mettre à jour une évaluation existante
    Evaluation updateEvaluation(Evaluation evaluation);

    Evaluation getEvaluationById(String idEvaluation);

    //
//    // Supprimer une évaluation par ID
    void deleteEvaluation(String idEvaluation);


    void submitEvaluationAnswers(String evaluationId, Map<String, Object> answers);


    List<Question> getQuestionsByEvaluation(String idEvaluation);


    public Evaluation addQuestionsToEvaluation(String idEvaluation, Set<String> quesIds);
}
