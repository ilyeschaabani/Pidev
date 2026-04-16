package tn.esprit.formationmicroservice.Entities;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import tn.esprit.formationmicroservice.Enumerations.CategoryResource;


import java.util.Date;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(collection = "Formations")
public class Formation {
    @Id
    String idFormation;
    String titreFormation;
    String description;
    Date dateDebut;
    Date dateFin;
    CategoryResource categorie;
    double prix;
    double rating;

}
