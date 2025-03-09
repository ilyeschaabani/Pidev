package tn.esprit.authenticationmicroservice.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.authenticationmicroservice.Entity.User;
import tn.esprit.authenticationmicroservice.Service.Authentication.AuthenticationServiceImpl;
import tn.esprit.authenticationmicroservice.dto.JwtAuthenticationResponse;
import tn.esprit.authenticationmicroservice.dto.RefreshTokenrequest;
import tn.esprit.authenticationmicroservice.dto.SignInRequest;
import tn.esprit.authenticationmicroservice.dto.SignUpRequest;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200") // Angular port
public class AuthentificationRestAPI {

    private final AuthenticationServiceImpl authenticationService;

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



}
