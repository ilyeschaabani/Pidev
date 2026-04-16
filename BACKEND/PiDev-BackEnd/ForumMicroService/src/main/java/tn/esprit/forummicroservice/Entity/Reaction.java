package tn.esprit.forummicroservice.Entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reaction {
    @Id
    private String id;
    private EmojiType emoji; // Enum for reactions like LIKE, HAHA, etc.
    private String userId; // User who reacted
    private String entityName; // "comment" or "eventTopic"
    private String entityId;
    private LocalDateTime createdAt = LocalDateTime.now(); // Timestamp of the reaction
    private LocalDateTime updatedAt;
}
