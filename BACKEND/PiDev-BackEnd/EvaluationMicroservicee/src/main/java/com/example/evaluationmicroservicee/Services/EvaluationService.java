package com.example.evaluationmicroservicee.Services;

import com.example.evaluationmicroservicee.Entity.*;
import com.example.evaluationmicroservicee.Repository.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EvaluationService implements IEvaluationService {
    @Autowired
    private EvaluationRepository evaluationRepository;
    @Autowired
    private com.example.evaluationmicroservicee.Repository.QuestionRepository QuestionRepository;

    @Autowired
    private com.example.evaluationmicroservicee.Repository.ReponseRepository ReponseRepository;

    @Autowired
    private QuizResultRepository quizResultRepository;
    @Autowired
    private UserRepository userRepository;


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


    // Méthode pour enregistrer le score d'un quiz
    public QuizResult saveQuizResult(String idEvaluation, int score) {
        QuizResult result = new QuizResult();
        result.setEvaluationId(idEvaluation);
        result.setScore(score);
        result.setCompletionDate(new Date());
        // result.setUserId(userId); // si vous ajoutez ce champ
        return quizResultRepository.save(result);
    }
//    public void updateUserPoints(String idUser, int score) {
//        User user = UserRepository.findById(idUser)
//                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
//
//        // Ajout des points
//        user.setTotalPoints(user.getTotalPoints() + score);
//
//        // Mise à jour du niveau (ex: 100 points par niveau)
//        user.setLevel(user.getTotalPoints() / 100 + 1);
//
//        // Vérification des badges
//        checkAndAssignBadges(user, score);
//
//        userRepository.save(user);
//    }

    private void checkAndAssignBadges(User user, int score) {
        // Logique pour attribuer des badges
        if (score >= 90 && !user.getBadges().contains("EXCELLENCE")) {
            user.getBadges().add("EXCELLENCE");
        }
        if (user.getTotalPoints() >= 500 && !user.getBadges().contains("VETERAN")) {
            user.getBadges().add("VETERAN");
        }
    }
//    @Override
//    public List<QuizResult> getUserQuizResults(String idUser) {
//        return quizResultRepository.findByUserId(idUser);
//    }

    public List<QuizResult> getUserQuizResults(String idUser) {
        System.out.println("Searching quiz results for user: " + idUser);
        List<QuizResult> results = quizResultRepository.findByUserId(idUser);
        System.out.println("Found " + results.size() + " results.");
        return results;
    }

        @Override
        public List<QuizResult> getResultsByEvaluation(String idEvaluation) {
            return quizResultRepository.findByIdEvaluation(idEvaluation);
        }


    public QuizResult evaluateAndSave(QuizResult submission) {
        // 1. Calcul du score
        int score = 0;
        int total = submission.getAnswers().size();

        for (UserAnswer ua : submission.getAnswers()) {
            // compare la bonne réponse
            if (ua.getSelectedOption().equals(ua.getCorrectAnswer())) {
                score++;
            }
        }

        // 2. Création de l'objet résultat
        QuizResult result = new QuizResult();
        result.setUserId(submission.getUserId());
        result.setEvaluationId(submission.getEvaluationId());
        result.setScore(score);
        result.setPercentage((score * 100) / total);
        result.setDateTaken(LocalDateTime.now());

        // 3. Sauvegarde dans Mongo
        return quizResultRepository.save(result);
    }

}




