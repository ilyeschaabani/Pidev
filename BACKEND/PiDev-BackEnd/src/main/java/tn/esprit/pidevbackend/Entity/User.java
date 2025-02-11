package tn.esprit.pidevbackend.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.pidevbackend.Entity.Enumeration.Role;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    String idUser;
    String nom;
    String prenom;
    String email;
    String password;
    Role role;
    String photo;
    String telephone;
    String adresse;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    Set<Ressources> ressources;
    @ManyToMany(mappedBy = "users",cascade = CascadeType.ALL)
    Set<Formation> formations;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    Set<Poste> postes;
    @ManyToMany(mappedBy = "users",cascade = CascadeType.ALL)
    Set<Event> events;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    Set<Reclamation> reclamations;

}
