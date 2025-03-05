package tn.esprit.projectmicroservice.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tn.esprit.projectmicroservice.Entity.Enumeration.StatutProjet;

import java.util.List;

@Document(collection = "projets") // Spécifie que c'est un document MongoDB
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Projet {
    @Id
    String idProjet;
    String titre;
    String description;
    String porteurProjet;
    String encadrant;
    Boolean espaceCollaboratif;
    StatutProjet statutProjet;
    String email;
    String telephone;
    String technologies;
    String objectifs;
    String benefices;
    String rejectionMotif; // Ajout du motif de rejet
    public Projet(String titre, String description, String porteurProjet, String encadrant, Boolean espaceCollaboratif, StatutProjet statutProjet, String email, String telephone, String technologies, String objectifs, String benefices,  String rejectionMotif) {
        this.titre = titre;
        this.description = description;
        this.porteurProjet = porteurProjet;
        this.encadrant = encadrant;
        this.espaceCollaboratif = espaceCollaboratif;
        this.statutProjet = statutProjet;
        this.email = email;
        this.telephone = telephone;
        this.technologies = technologies;
        this.objectifs = objectifs;
        this.benefices = benefices;
        this.rejectionMotif = rejectionMotif;
    }

}
