package tn.esprit.projectmicroservice.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tn.esprit.projectmicroservice.Entity.ChatMessage;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
}
