package tn.esprit.forummicroservice.Repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import tn.esprit.forummicroservice.Entity.EmojiType;
import tn.esprit.forummicroservice.Entity.Reaction;

import java.util.List;

public interface ReactionRepository extends MongoRepository<Reaction, String> {
    List<Reaction> findByEntityNameAndEntityId(String entityName, String entityId);
    Reaction findByEntityNameAndEntityIdAndUserIdAndEmoji(String entityName, String entityId, String userId, EmojiType emoji);
   List< Reaction> findByEntityNameAndEntityIdAndUserId(String entityName, String entityId, String userId);

}
