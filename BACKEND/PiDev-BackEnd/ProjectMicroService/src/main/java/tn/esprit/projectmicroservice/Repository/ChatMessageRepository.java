package tn.esprit.projectmicroservice.Repository;

import tn.esprit.projectmicroservice.Entity.ChatMessage;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {

    List<ChatMessage> findByProjectIdOrderByTimestampAsc(String projectId);

    List<ChatMessage> findByProjectIdOrderByTimestampDesc(String projectId, Pageable pageable);
}