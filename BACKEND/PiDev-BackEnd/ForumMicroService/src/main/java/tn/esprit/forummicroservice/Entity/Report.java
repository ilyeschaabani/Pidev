package tn.esprit.forummicroservice.Entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reports") // Collection MongoDB
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report {

    @Id
    private String id;
    private String eventTopicId; // Référence vers EventTopic
    private String commentId; // Référence vers EventTopic
    private String userId;
    private String raison;
    private ReportStatus status = ReportStatus.WAITING; // Statut par défaut : en attente
}
