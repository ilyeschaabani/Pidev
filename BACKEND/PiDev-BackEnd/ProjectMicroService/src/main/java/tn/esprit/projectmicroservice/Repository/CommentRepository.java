package tn.esprit.projectmicroservice.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tn.esprit.projectmicroservice.Entity.Comment;
import tn.esprit.projectmicroservice.Entity.Task;

public interface CommentRepository  extends MongoRepository<Comment, String> {

}
