package tn.esprit.forummicroservice.Entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "events_topics") // Collection MongoDB
@Data // Génère getters, setters, toString, equals, hashCode
@NoArgsConstructor // Constructeur vide
@AllArgsConstructor // Constructeur avec tous les champs
public class EventTopic {

    @Id
    private String id;
    private String title;
    private String description;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime eventDate; // Peut être null si c'est un topic
    private boolean isEvent; // true = Event, false = Topic
    private String location; // Peut être null si c'est un topic
    private List<String> commentIds; // Liste d'ID des commentaires associés à ce post
    private List<Reaction> reactions=new ArrayList<>();
    private List<String> reportIds; // Liste des IDs des reports liés à cet event/topic
    private String userId; // User who reacted
    private String media;
    private FileType mediaType;

}


