package tn.esprit.authenticationmicroservice.Service.OAuth2;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import tn.esprit.authenticationmicroservice.Entity.User;
import tn.esprit.authenticationmicroservice.Service.User.UserService;

import java.util.Collections;
import java.util.List;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserService userService;

    public CustomOAuth2UserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // Extract user details from OAuth2 provider (Google, Facebook, etc.)
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");


        // Register or update the user in your database
        User user = userService.processOAuth2User(email, name);
        List<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority(user.getRole().name())
        );

        return new DefaultOAuth2User(authorities, oAuth2User.getAttributes(), "email");
    }
}
