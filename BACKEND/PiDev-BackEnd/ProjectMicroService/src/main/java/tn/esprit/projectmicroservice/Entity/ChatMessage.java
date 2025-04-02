package tn.esprit.projectmicroservice.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Document(collection = "chat_messages")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatMessage {
    @Id
    private String id;
    private String projectId;
    private String sender;
    private String content;
    private Date timestamp;
    // Getters/Setters



}
