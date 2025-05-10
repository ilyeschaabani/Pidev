package tn.esprit.projectmicroservice.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tn.esprit.projectmicroservice.Entity.Milestone;

import java.util.List;

public interface MilestoneRepository extends MongoRepository<Milestone, String> {
    List<Milestone> findByProjectId(String projectId);
}
