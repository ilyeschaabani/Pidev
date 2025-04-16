package tn.esprit.authenticationmicroservice.Service.User;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tn.esprit.authenticationmicroservice.Entity.Enum.Role;
import tn.esprit.authenticationmicroservice.Entity.User;
import tn.esprit.authenticationmicroservice.Repository.UserRepository;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return email -> userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + email));
    }
    public void updatePassword(String email, String newPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
        user.setPassword(newPassword);
        userRepository.save(user);
    }
    public User processOAuth2User(String email, String name) {
        // Check if user exists
        User user = userRepository.findByEmail(email)
                .orElseGet(() -> {
                    // Create new user if not found
                    User newUser = new User();
                    newUser.setEmail(email);
                    newUser.setNom(name);
                    newUser.setRole(Role.CONSULTANT); // Set default role
                    return userRepository.save(newUser);
                });

        return user;
    }

}
