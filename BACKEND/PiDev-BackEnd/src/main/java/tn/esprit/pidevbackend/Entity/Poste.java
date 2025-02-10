package tn.esprit.pidevbackend.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.pidevbackend.Entity.Enumeration.Topics;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Poste {
    @Id
    String idPoste;
    String titre;
    String Contenu;
    String id_user;
    String date;
    Topics topic;
    @ManyToOne
    User user;
    @OneToOne
    Likes likes;
    @OneToMany
    Set<Comment> comments;

}
