package tn.esprit.authenticationmicroservice.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.authenticationmicroservice.Entity.User;
import tn.esprit.authenticationmicroservice.Service.Authentication.AuthenticationServiceImpl;
import tn.esprit.authenticationmicroservice.dto.JwtAuthenticationResponse;
import tn.esprit.authenticationmicroservice.dto.RefreshTokenrequest;
import tn.esprit.authenticationmicroservice.dto.SignInRequest;
import tn.esprit.authenticationmicroservice.dto.SignUpRequest;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
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
