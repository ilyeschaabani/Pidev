package com.example.evaluationmicroservicee.Repository;

import com.example.evaluationmicroservicee.Entity.Certif;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertifRepository extends MongoRepository<Certif, String> {

}
