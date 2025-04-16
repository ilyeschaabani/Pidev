package com.example.evaluationmicroservicee.Controller;

import com.example.evaluationmicroservicee.Entity.QuizResult;
import com.example.evaluationmicroservicee.Services.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz-results")
@CrossOrigin(origins = "http://localhost:4200")
public class QuizResultController {


    @Autowired
    private EvaluationService evaluationService;

    public QuizResultController(EvaluationService evluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping
    public QuizResult saveQuizResult(
            @RequestParam String idEvaluation,
            @RequestParam int score) {
        return evaluationService.saveQuizResult(idEvaluation, score);
    }
    @GetMapping("/user/{idUser}")
    public List<QuizResult> getUserResults(@PathVariable String idUser) {
        return evaluationService.getUserQuizResults(idUser);
    }
    @GetMapping("/by-evaluation/{evaluationId}")
    public ResponseEntity<List<QuizResult>> getResultsByEvaluation(@PathVariable String idEvaluation) {
        List<QuizResult> results = evaluationService.getResultsByEvaluation(idEvaluation);
        return ResponseEntity.ok(results);
    }




    @PostMapping("/submit")
    public ResponseEntity<QuizResult> submitQuiz(@RequestBody QuizResult submission) {
        QuizResult savedResult = evaluationService.evaluateAndSave(submission);
        return ResponseEntity.ok(savedResult);
    }




}