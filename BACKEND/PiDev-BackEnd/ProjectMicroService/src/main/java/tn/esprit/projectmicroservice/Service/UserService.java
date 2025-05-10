package tn.esprit.projectmicroservice.Service;

import tn.esprit.projectmicroservice.Entity.User;
import tn.esprit.projectmicroservice.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;  // Dépendance à votre repository d'utilisateurs

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Méthode pour obtenir un utilisateur par ID
    public User getUserById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'ID : " + userId));
    }

    // Méthode pour vérifier si l'utilisateur est un administrateur
    public boolean isAdmin(User user) {
        return "ADMIN".equals(user.getRole());  // Si le rôle est ADMIN, alors l'utilisateur est un administrateur
    }

    // Méthode pour vérifier si l'utilisateur est un encadrant
    public boolean isEncadrant(User user) {
        return "ENCADRANT".equals(user.getRole()); // Si le rôle est ENCADRANT, alors l'utilisateur est encadrant
    }

    // Méthode pour vérifier si l'utilisateur est un étudiant
    public boolean isEtudiant(User user) {
        return "ETUDIANT".equals(user.getRole()); // Si le rôle est ETUDIANT, alors l'utilisateur est étudiant
    }
}
