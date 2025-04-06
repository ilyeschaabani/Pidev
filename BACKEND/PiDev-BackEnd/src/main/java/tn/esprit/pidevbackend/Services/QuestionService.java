package tn.esprit.pidevbackend.Services;


import tn.esprit.pidevbackend.Entity.Evaluation;
import tn.esprit.pidevbackend.Entity.Question;

import java.util.List;
import java.util.Set;

public interface QuestionService {
	
	public Question addQuestion(Question question);
	
	public Question updateQuestion(Question question);
	
	public List<Question> getQuestions();
	
	public Question getQuestion( String quesId);

	public Set<Question> getQuestionsByEvaluationId(String idEvaluation);
	public void deleteQuestion(String quesId);

}
