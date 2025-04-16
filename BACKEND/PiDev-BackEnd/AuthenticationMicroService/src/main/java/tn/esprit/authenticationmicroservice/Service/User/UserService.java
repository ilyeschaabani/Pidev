package tn.esprit.authenticationmicroservice.Service.User;

import org.springframework.security.core.userdetails.UserDetailsService;
import tn.esprit.authenticationmicroservice.Entity.User;

public interface UserService  {
    UserDetailsService userDetailsService();
    void updatePassword(String email, String newPassword);
    User processOAuth2User(String email, String name);
}
