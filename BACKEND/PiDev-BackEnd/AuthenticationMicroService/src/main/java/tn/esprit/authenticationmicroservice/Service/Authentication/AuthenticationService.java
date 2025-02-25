package tn.esprit.authenticationmicroservice.Service.Authentication;


import tn.esprit.authenticationmicroservice.Entity.User;
import tn.esprit.authenticationmicroservice.dto.JwtAuthenticationResponse;
import tn.esprit.authenticationmicroservice.dto.RefreshTokenrequest;
import tn.esprit.authenticationmicroservice.dto.SignInRequest;
import tn.esprit.authenticationmicroservice.dto.SignUpRequest;

public interface AuthenticationService {
    User singUp(SignUpRequest signUpRequest);
    JwtAuthenticationResponse login(SignInRequest signInRequest);
    JwtAuthenticationResponse refreshToken(RefreshTokenrequest refreshTokenrequest);
}
