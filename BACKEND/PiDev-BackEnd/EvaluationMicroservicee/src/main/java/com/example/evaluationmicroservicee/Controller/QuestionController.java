package com.example.evaluationmicroservicee.Controller;

import com.example.evaluationmicroservicee.Entity.Question;
import com.example.evaluationmicroservicee.Services.EvaluationService;
import com.example.evaluationmicroservicee.Services.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/questions")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private EvaluationService evaluationService;

	@PostMapping("addquestion")
	public Question createQuestion(@RequestBody Question question) {
	return questionService.addQuestion(question);
	}
    @GetMapping("listquestion")
    public List <Question> getAllQuestions() {
        return questionService.getQuestions();
    }

	@PutMapping("/{quesId}")
	public ResponseEntity<Question> updateQuestion(@PathVariable  String quesId, @RequestBody Question question) {
		try {
			// Vérifier que l'ID correspond
			if (!quesId.toString().equals(question.getQuesId())) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

			Question updatedQuestion = questionService.updateQuestion(question);
			return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/by-evaluation/{idEvaluation}")
	public ResponseEntity<List<Question>> getQuestionsByEvaluation(@PathVariable String idEvaluation) {
		Set<Question> questions = questionService.getQuestionsByEvaluationId(idEvaluation);

		if (questions.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(new ArrayList<>(questions));
	}
	@GetMapping("/{quesId}")
	public ResponseEntity<Question> getQuestionById(@PathVariable  String quesId) {
		try {
			Question question = questionService.getQuestion(quesId);
			return new ResponseEntity<>(question, HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{quesId}")
	public ResponseEntity<Void> deleteQuestion(@PathVariable String quesId) {

        questionService.deleteQuestion(quesId);
        return ResponseEntity.noContent().build();
    }

    }
