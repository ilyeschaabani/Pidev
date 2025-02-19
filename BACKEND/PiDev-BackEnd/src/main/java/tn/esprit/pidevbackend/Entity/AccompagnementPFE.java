package tn.esprit.pidevbackend.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "accompagnementsPFE") // Spécifie la collection MongoDB
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccompagnementPFE {

    @Id
    String idAccompagnement; // MongoDB génère un ObjectId si laissé vide

    String etudiant;
    String encadrant;
    String sujet;
    Float avancement;

    // Stocke uniquement les IDs des utilisateurs (relation ManyToMany en SQL)
    List<String> participantsPFE;
}
