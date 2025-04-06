package tn.esprit.pidevbackend.Serviceuser.Authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.esprit.pidevbackend.Entity.SignUpRequest;
import tn.esprit.pidevbackend.Entity.User;
import tn.esprit.pidevbackend.Repository.UserRepository;

import java.util.HashMap;

@Service

public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public User singUp(SignUpRequest signUpRequest) {
        return null;
    }

}
