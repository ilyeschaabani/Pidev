package tn.esprit.pidevbackend.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.pidevbackend.Entity.Evaluation;
import tn.esprit.pidevbackend.Entity.Question;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {

    Set<Question> findQuestionsByIdEvaluation(String idEvaluation);
    Set<Question> findByIdEvaluation(String idEvaluation);
    @Query("SELECT q FROM Question q WHERE q.evaluation.idEvaluation =evalId")
    Set<Question> findByEvaluationId(@Param("idEvaluation") String idEvaluation);
    Optional<Question> findByQuesId(String quesId);
}
