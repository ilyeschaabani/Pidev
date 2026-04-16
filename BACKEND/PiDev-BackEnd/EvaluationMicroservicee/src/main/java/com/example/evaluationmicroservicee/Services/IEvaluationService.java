package com.example.evaluationmicroservicee.Services;


import com.example.evaluationmicroservicee.Entity.Evaluation;
import com.example.evaluationmicroservicee.Entity.Question;
import com.example.evaluationmicroservicee.Entity.QuizResult;

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
    public QuizResult saveQuizResult(String idEvaluation, int score);

    void submitEvaluationAnswers(String evaluationId, Map<String, Object> answers);

    List<Question> getQuestionsByEvaluation(String idEvaluation);

    public Evaluation addQuestionsToEvaluation(String idEvaluation, Set<String> quesIds);
    List<QuizResult> getUserQuizResults(String idUser);

    List<QuizResult> getResultsByEvaluation(String idEvaluation);

    public QuizResult evaluateAndSave(QuizResult submission);
}

