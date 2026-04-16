package tn.esprit.forummicroservice.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tn.esprit.forummicroservice.Entity.Report;

import java.util.List;

public interface ReportRepository extends MongoRepository<Report, String> {
    List<Report> findByStatus(String status);
    List<Report> findByEventTopicId(String eventTopicId);
}
