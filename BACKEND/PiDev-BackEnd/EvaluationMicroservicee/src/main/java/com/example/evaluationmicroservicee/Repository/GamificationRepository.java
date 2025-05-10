package com.example.evaluationmicroservicee.Repository;

import com.example.evaluationmicroservicee.Entity.Gamification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamificationRepository extends MongoRepository<Gamification, String> {
}
