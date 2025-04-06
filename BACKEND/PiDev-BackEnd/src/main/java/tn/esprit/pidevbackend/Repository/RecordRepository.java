package tn.esprit.pidevbackend.Repository;

import org.springframework.data.mongodb.core.MongoAdminOperations;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidevbackend.Entity.AttemptedQuizRecords;
import tn.esprit.pidevbackend.Entity.Evaluation;

import java.util.List;

@Repository
public interface RecordRepository extends MongoRepository<AttemptedQuizRecords, String> {
    List<AttemptedQuizRecords> findByEvaluationId(String evaluationId);
}
