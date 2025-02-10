package tn.esprit.pidevbackend.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.pidevbackend.Entity.Enumeration.CategoryResource;
import tn.esprit.pidevbackend.Entity.Enumeration.TypeResource;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Ressources {
    @Id
    String idRessource;
    String titre;
    String description;
    TypeResource type;
    Date date;
    CategoryResource category;
    @ManyToOne
    User user;
}
