package com.example.evaluationmicroservicee.Repository;

import com.example.evaluationmicroservicee.Entity.Reponse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReponseRepository  extends MongoRepository<Reponse, String> {
}
