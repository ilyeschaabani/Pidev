package tn.esprit.authenticationmicroservice.Service.OAuth2;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import tn.esprit.authenticationmicroservice.Entity.User;

import java.util.Collections;
import java.util.Map;

public class CustomOAuth2User extends DefaultOAuth2User {
    private final User user;

    public CustomOAuth2User(User user, Map<String, Object> attributes) {
        super(
                Collections.singleton(new SimpleGrantedAuthority(user.getRole().name())),
                attributes,
                "email" // Use "sub" for Google, "id" for Facebook
        );
        this.user = user;
    }

    @Override
    public String getName() {
        return user.getEmail();
    }

    public User getUser() {
        return user;
    }

}
