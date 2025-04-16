package com.example.evaluationmicroservicee.Services;

import com.example.evaluationmicroservicee.Entity.Question;
import com.example.evaluationmicroservicee.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;
/////////////
	@Override
	public Question addQuestion(Question question) {
		return this.questionRepository.save(question);
	}

	@Override
	public Question updateQuestion(Question question) {
		if(!questionRepository.existsById(question.getQuesId())) {
			throw new RuntimeException("Question non trouvée avec ID: " + question.getQuesId());
		}
		return this.questionRepository.save(question);
	}

	@Override
	public List<Question> getQuestions() {
		return questionRepository.findAll();
	}

	@Override
	public Question getQuestion( String quesId) {
		Optional<Question> question = this.questionRepository.findById(String.valueOf(quesId));
		return question.orElseThrow(() -> new RuntimeException("Question non trouvée avec ID: " + quesId));
	}

	@Override
	public Set<Question> getQuestionsByEvaluationId(String idEvaluation) {
		return questionRepository.findByIdEvaluation(idEvaluation);
	}

	@Override
	public void deleteQuestion(String quesId ) {

		questionRepository.deleteById(quesId);
	}



}