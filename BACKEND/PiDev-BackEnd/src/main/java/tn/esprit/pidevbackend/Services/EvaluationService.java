package tn.esprit.pidevbackend.Services;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.pidevbackend.Entity.Evaluation;
import tn.esprit.pidevbackend.Entity.Question;
import tn.esprit.pidevbackend.Entity.Reponse;
import tn.esprit.pidevbackend.Repository.EvaluationRepository;
import tn.esprit.pidevbackend.Repository.QuestionRepository;
import tn.esprit.pidevbackend.Repository.ReponseRepository;

import java.util.*;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class EvaluationService implements IEvaluationService {
    @Autowired
    private EvaluationRepository evaluationRepository;
    @Autowired
    private QuestionRepository QuestionRepository;

    @Autowired
    private ReponseRepository ReponseRepository;

    @Override
    public Evaluation createEvaluation(Evaluation evaluation) {
        return evaluationRepository.save(evaluation);
    }

    //
    @Override
    public List<Evaluation> getAllEvaluations() {
        return evaluationRepository.findAll();
    }

//    public Question createQuestion(Question question, String evaluationId) {
//        Optional<Evaluation> evaluationOpt = evaluationRepository.findById(evaluationId);
//        if (evaluationOpt.isPresent()) {
//            Evaluation evaluation = evaluationOpt.get();
//            question.setEvaluation(evaluation);
//            return QuestionRepository.save(question);
//        } else {
//            throw new RuntimeException("Évaluation non trouvée");
//        }
//    }

    @Override
    public List<Question> getQuestionsByEvaluation(String idEvaluation) {
        return List.of();
    }


    @Override
    public Evaluation updateEvaluation(Evaluation evaluation) {
        if (evaluationRepository.existsById(String.valueOf(evaluation.getIdEvaluation()))) {
            return evaluationRepository.save(evaluation);
        } else {
            throw new RuntimeException("Evaluation not found");
        }
    }

    public Evaluation getEvaluationById(String idEvaluation) {
        return evaluationRepository.findFirstByIdEvaluation(idEvaluation)
                .orElseThrow(() -> new RuntimeException("Evaluation non trouvée avec id: " + idEvaluation));
    }


    @Override
    public void deleteEvaluation(String idEvaluation) {
        evaluationRepository.deleteById(idEvaluation);
    }

    @Override
    public void submitEvaluationAnswers(String idEvaluation, Map<String, Object> answers) {
        Optional<Evaluation> evaluationOpt = evaluationRepository.findById(idEvaluation);

        if (evaluationOpt.isPresent()) {
            System.out.println("Réponses reçues pour l'évaluation " + idEvaluation + ": " + answers);
            // Logique pour stocker les réponses...
        } else {
            throw new RuntimeException("Évaluation non trouvée avec l'ID: " + idEvaluation);
        }
    }

    //////////////////////
    @Override
    @Transactional
    public Evaluation addQuestionsToEvaluation(String idEvaluation, Set<String> quesIds) {
        // 1. Trouver l'évaluation
        Evaluation evaluation = evaluationRepository.findByIdEvaluation(idEvaluation)
                .orElseThrow(() -> new RuntimeException("Évaluation non trouvée: " + idEvaluation));

        // 2. Vérifier chaque question
        Set<Question> questions = new HashSet<>();
        for (String quesId : quesIds) {
            Question question = QuestionRepository.findByQuesId(quesId)
                    .orElseThrow(() -> new RuntimeException("Question non trouvée: " + quesId));

            // Vérification cruciale
            if (question.getQuesId() == null) {
                throw new IllegalStateException("La question a un ID null");
            }

            // Mettre à jour la référence
            question.setIdEvaluation(idEvaluation);
            QuestionRepository.save(question);

            questions.add(question);
        }
        evaluation.getQuestions().clear();
        evaluation.getQuestions().addAll(
                quesIds.stream()
                        .map(id -> QuestionRepository.findByQuesId(id).orElseThrow())
                        .collect(Collectors.toSet())
        );
        return evaluationRepository.save(evaluation);
    }
}