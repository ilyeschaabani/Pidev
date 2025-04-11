package tn.esprit.authenticationmicroservice.Service.User;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService  {
    UserDetailsService userDetailsService();
    void updatePassword(String email, String newPassword);
}
