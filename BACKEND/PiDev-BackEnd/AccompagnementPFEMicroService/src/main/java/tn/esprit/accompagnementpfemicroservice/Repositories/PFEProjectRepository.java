package tn.esprit.accompagnementpfemicroservice.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import tn.esprit.accompagnementpfemicroservice.Entities.PFEProject;

public interface PFEProjectRepository extends MongoRepository<PFEProject, String> {}