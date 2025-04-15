package tn.esprit.accompagnementpfemicroservice.Services;

import org.springframework.stereotype.Service;
import tn.esprit.accompagnementpfemicroservice.Entities.Role;
import tn.esprit.accompagnementpfemicroservice.Entities.User;
import tn.esprit.accompagnementpfemicroservice.Repositories.UserRepository;

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

    public boolean isAdmin(User user) {
        return user.getRole() == Role.ADMIN;
    }

    public boolean isEncadrant(User user) {
        return user.getRole() == Role.MENTOR;
    }

    public boolean isEtudiant(User user) {
        return user.getRole() == Role.STUDENT;
    }

}
