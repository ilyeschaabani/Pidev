package com.example.evaluationmicroservicee.Repository;

import com.example.evaluationmicroservicee.Entity.AttemptedQuizRecords;
import org.springframework.data.mongodb.core.MongoAdminOperations;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends MongoRepository<AttemptedQuizRecords, String> {
    List<AttemptedQuizRecords> findByEvaluationId(String evaluationId);
}
