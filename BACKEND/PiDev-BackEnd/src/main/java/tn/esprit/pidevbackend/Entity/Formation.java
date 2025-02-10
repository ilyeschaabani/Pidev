package tn.esprit.pidevbackend.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Formation {
    @Id
    String idFormation;
    String image;
    String titreFormation;
    String description;
    Date dateDebut;
    Date dateFin;
    String video;
    String categorie;

    @OneToOne
    Evaluation evaluation;
    @ManyToMany(cascade = CascadeType.ALL)
    Set<User> users;
    @OneToOne (mappedBy = "formation")
    Rating rating;
    @ManyToOne
    Panier panier;

}
