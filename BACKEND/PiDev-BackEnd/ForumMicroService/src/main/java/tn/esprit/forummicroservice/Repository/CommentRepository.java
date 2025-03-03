package tn.esprit.forummicroservice.Repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import tn.esprit.forummicroservice.Entity.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {
    // Vous pouvez ajouter des méthodes personnalisées si nécessaire.
}

