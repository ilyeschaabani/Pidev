package tn.esprit.pidevbackend.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidevbackend.Entity.Evaluation;
import tn.esprit.pidevbackend.Entity.Question;
import tn.esprit.pidevbackend.Entity.Reponse;
import tn.esprit.pidevbackend.Services.EvaluationService;
import tn.esprit.pidevbackend.Services.QuestionService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/evaluations")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class EvaluationController {
@Autowired
    private EvaluationService evaluationService;
    @Autowired
    private QuestionService  questionRepository;

    // Créer une nouvelle évaluation
    @PostMapping("/add")
    public Evaluation createEvaluation(@RequestBody Evaluation evaluation) {
        return evaluationService.createEvaluation(evaluation);
    }


    // Lire toutes les évaluations
    @GetMapping("/listeval")
    public List<Evaluation> getAllEvaluations() {
        return evaluationService.getAllEvaluations();
    }

    //
//    // Lire une évaluation par ID
//    @GetMapping("/{id}")
//    public ResponseEntity<Evaluation> getEvaluationById(@PathVariable String id) {
//        Optional<Evaluation> evaluation = evaluationService.getEvaluationById(id);
//        return evaluation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
// Mettre à jour une évaluation avec l'id passé dans l'URL
    @PutMapping("/{idEvaluation}")
    public Evaluation updateEvaluation(@PathVariable("idEvaluation") String id, @RequestBody Evaluation evaluation) {
        evaluation.setIdEvaluation(String.valueOf(id));
        return evaluationService.updateEvaluation(evaluation);
    }


    //    // Supprimer une évaluation
    @DeleteMapping("/{idEvaluation}")
    public ResponseEntity<Void> deleteEvaluation(@PathVariable String idEvaluation) {
        evaluationService.deleteEvaluation(idEvaluation);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{idEvaluation}")
    public Evaluation getEvaluationById(@PathVariable String idEvaluation) {
        return evaluationService.getEvaluationById(idEvaluation);
    }


    @PostMapping("/{idEvaluation}/submit")
    public ResponseEntity<?> submitEvaluationAnswers(
            @PathVariable String idEvaluation,
            @RequestBody Map<String, Object> payload) {

        List<Object> answers = (List<Object>) payload.get("answers");
        System.out.println("Réponses reçues pour l'évaluation " + idEvaluation + ": " + answers);

        return ResponseEntity.ok("Réponses enregistrées avec succès !");
    }

    @PostMapping("/{idEvaluation}/questions")
    public ResponseEntity<Evaluation> addQuestionsToEvaluation(@PathVariable String idEvaluation, @RequestBody Set<String> quesIds)
          {

        Evaluation evaluation = evaluationService.addQuestionsToEvaluation(idEvaluation, quesIds);
        return ResponseEntity.ok(evaluation);
    }
}
//    @PostMapping("/{evaluationId}")
//    public ResponseEntity<Question> createQuestion(@RequestBody Question question, @PathVariable String evaluationId) {
//        Question createdQuestion = evaluationService.createQuestion(question, evaluationId);
//        return new ResponseEntity<>(createdQuestion, HttpStatus.CREATED);
//    }
//
//    // Obtenir toutes les questions d'une évaluation
//    @GetMapping("/evaluation/{evaluationId}")
//    public ResponseEntity<List<Question>> getQuestionsByEvaluation(@PathVariable String evaluationId) {
//        List<Question> questions = evaluationService.getQuestionsByEvaluation(evaluationId);
//        return new ResponseEntity<>(questions, HttpStatus.OK);
//    }
//   @PostMapping("/{questionId}")
//    public ResponseEntity<Reponse> createReponse(@RequestBody Reponse reponse, @PathVariable Long questionId) {
//        Reponse createdReponse = evaluationService.createReponse(reponse, questionId);
//        return new ResponseEntity<>(createdReponse, HttpStatus.CREATED);
//    }


