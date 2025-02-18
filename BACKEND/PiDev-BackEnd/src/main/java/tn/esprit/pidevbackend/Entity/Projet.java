package tn.esprit.pidevbackend.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.pidevbackend.Entity.Enumeration.StatutProjet;

import java.util.Set;

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

@ManyToMany
Set<User> participantProjeccts;

}
