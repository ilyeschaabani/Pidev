package tn.esprit.authenticationmicroservice.Service.Authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.esprit.authenticationmicroservice.Entity.User;
import tn.esprit.authenticationmicroservice.Repository.UserRepository;
import tn.esprit.authenticationmicroservice.Service.JWT.JWTService;
import tn.esprit.authenticationmicroservice.dto.JwtAuthenticationResponse;
import tn.esprit.authenticationmicroservice.dto.RefreshTokenrequest;
import tn.esprit.authenticationmicroservice.dto.SignInRequest;
import tn.esprit.authenticationmicroservice.dto.SignUpRequest;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;


    public User singUp(SignUpRequest signUpRequest) {
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRole(signUpRequest.getRole());
        user.setNom(signUpRequest.getNom());
        user.setPrenom(signUpRequest.getPrenom());
        user.setTelephone(signUpRequest.getTelephone());
        user.setAdresse(signUpRequest.getAdresse());
        return userRepository.save(user);
    }

    public JwtAuthenticationResponse login(SignInRequest signInRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));
        var  user = userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
        var token = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(),user);
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(token);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse;
    }
    public JwtAuthenticationResponse refreshToken(RefreshTokenrequest refreshTokenrequest) {
        String userEmail = jwtService.extractUsername(refreshTokenrequest.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("User not found"));
        if (jwtService.validateToken(refreshTokenrequest.getToken(),user)) {
            var token = jwtService.generateToken(user);
            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setToken(token);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenrequest.getToken());
            return jwtAuthenticationResponse;
        }
        return null;
    }

    public Map<String, Object> getCurrentUser(String token) {
        // Extract token (strip "Bearer ")
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        String username = jwtService.extractUsername(token);
        User user = userRepository.findByEmail(username) // or findByUsername depending on your implementation
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("id", user.getIdUser());
        userInfo.put("nom", user.getNom());
        userInfo.put("prenom", user.getPrenom());
        userInfo.put("telephone", user.getTelephone());
        userInfo.put("adresse", user.getAdresse());
        userInfo.put("email", user.getEmail());
        userInfo.put("username", user.getUsername());
        userInfo.put("roles", user.getRole());

        return userInfo;
    }

}
