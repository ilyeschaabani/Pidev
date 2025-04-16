package tn.esprit.authenticationmicroservice.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.authenticationmicroservice.Entity.User;
import tn.esprit.authenticationmicroservice.Repository.UserRepository;
import tn.esprit.authenticationmicroservice.Service.Authentication.AuthenticationServiceImpl;
import tn.esprit.authenticationmicroservice.dto.JwtAuthenticationResponse;
import tn.esprit.authenticationmicroservice.dto.RefreshTokenrequest;
import tn.esprit.authenticationmicroservice.dto.SignInRequest;
import tn.esprit.authenticationmicroservice.dto.SignUpRequest;

import java.util.List;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200") // Angular port
public class AuthentificationRestAPI {

    private final AuthenticationServiceImpl authenticationService;
    private final UserRepository userRepository;


    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@Valid @RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authenticationService.singUp(signUpRequest));
    }
    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@Valid @RequestBody SignInRequest signInRequest){
        return ResponseEntity.ok(authenticationService.login(signInRequest));
    }
    @PostMapping("/refreshToken")
    public ResponseEntity<JwtAuthenticationResponse> refreshToken(@Valid @RequestBody RefreshTokenrequest refreshTokenrequest){
        return ResponseEntity.ok(authenticationService.refreshToken( refreshTokenrequest));
    }
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(authenticationService.getCurrentUser(token));
    }
    @GetMapping("/encadrants")
    public List<User> getEncadrants() {
        return userRepository.findByRole("ENCADRANT");
    }
}
