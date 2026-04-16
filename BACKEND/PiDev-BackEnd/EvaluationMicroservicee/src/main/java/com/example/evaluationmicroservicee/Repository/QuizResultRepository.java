package com.example.evaluationmicroservicee.Repository;


import com.example.evaluationmicroservicee.Entity.QuizResult;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface QuizResultRepository extends MongoRepository<QuizResult, String> {
    List<QuizResult> findByUserId(String idUser);
    List<QuizResult> findByEvaluationId(String idEvaluation);

    List<QuizResult> findByIdEvaluation(String idEvaluation);
}
