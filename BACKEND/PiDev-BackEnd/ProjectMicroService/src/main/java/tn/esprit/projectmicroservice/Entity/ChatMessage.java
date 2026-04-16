// ChatMessage.java
package tn.esprit.projectmicroservice.Entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ChatMessage") // Spécifie que c'est un document MongoDB

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessage {
    private String sender;
    private String receiver; // Peut être null pour les messages de groupe
    private String content;
    private String projectId; // Optionnel si tu veux faire des salons par projet
    private String timestamp;
}
