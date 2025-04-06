package tn.esprit.pidevbackend.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidevbackend.Entity.*;
import tn.esprit.pidevbackend.Serviceuser.Authentication.AuthenticationServiceImpl;

@RestController
@RequestMapping("/api/auth")

@CrossOrigin(origins = "http://localhost:4200") // Angular port
public class AuthentificationRestAPI {

    private final AuthenticationServiceImpl authenticationService;

    public AuthentificationRestAPI(AuthenticationServiceImpl authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@Valid @RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authenticationService.singUp(signUpRequest));
    }




}
