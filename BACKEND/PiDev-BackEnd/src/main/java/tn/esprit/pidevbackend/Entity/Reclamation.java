package tn.esprit.pidevbackend.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.pidevbackend.Entity.Enumeration.StatutRec;
import tn.esprit.pidevbackend.Entity.Enumeration.TypeReclamation;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Reclamation {
    @Id
    String idReclamation;
    String Description;
    Date dateReclamation;
    StatutRec etatReclamation;
    TypeReclamation typeReclamation;
}
