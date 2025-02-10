package tn.esprit.pidevbackend.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.pidevbackend.Entity.Enumeration.StatutProjet;

@Entity
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
String Encadrant;
Boolean espaceCollaboratif;
StatutProjet statutProjet;

}
