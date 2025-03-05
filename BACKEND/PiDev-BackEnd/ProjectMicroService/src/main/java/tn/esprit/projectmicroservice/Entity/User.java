package tn.esprit.projectmicroservice.Entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private String id; // ID unique de l'utilisateur
    private String username; // Le nom d'utilisateur
    private String role; // Le rôle de l'utilisateur, ex: "ADMIN", "ETUDIANT", "ENCADRANT"
}
