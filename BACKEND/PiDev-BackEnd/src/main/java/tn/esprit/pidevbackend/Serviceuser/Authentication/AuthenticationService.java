package tn.esprit.pidevbackend.Serviceuser.Authentication;


import tn.esprit.pidevbackend.Entity.SignUpRequest;
import tn.esprit.pidevbackend.Entity.User;

public interface AuthenticationService {
    User singUp(SignUpRequest signUpRequest);

}
