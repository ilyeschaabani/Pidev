package tn.esprit.authenticationmicroservice.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import tn.esprit.authenticationmicroservice.Service.JWT.JWTService;
import tn.esprit.authenticationmicroservice.Service.OAuth2.CustomOAuth2UserService;
import tn.esprit.authenticationmicroservice.Service.User.UserService;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final UserService userService;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final JWTService jwtUtils;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for stateless APIs
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/oauth2/**", // Allow OAuth2 endpoints
                                "/login/oauth2/code/**" // OAuth2 redirect URL
                        ).permitAll()
                        .requestMatchers("api/auth/**").permitAll()
                        .requestMatchers("AuthenticationMicroService/**").permitAll()
                        .requestMatchers("/forgotPassword/**").permitAll()
                        .requestMatchers("/h2-console/**").permitAll() // Allow access to H2 console
                        .requestMatchers("/api/Admin/**").hasAnyAuthority("ADMIN")
                        .requestMatchers("/api/Etudiant/**").hasAnyAuthority("ETUDIANT")
                        .requestMatchers("/api/Encadrant/**").hasAnyAuthority("ENCADRANT")
                        .requestMatchers("/api/Consultant/**").hasAnyAuthority("CONSULTANT")
                        .anyRequest().authenticated() // Require authentication for all other endpoints
                )
                .oauth2Login(oauth -> oauth // Add OAuth2 configuration
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuth2UserService) // Use custom OAuth2 user service
                        )
                        .successHandler((request, response, authentication) -> {
                            // Redirect to frontend with JWT token after OAuth2 login
                            String jwtToken = jwtUtils.generateJwtTokenForOAuthUser(authentication);
                            response.sendRedirect("http://localhost:4200?token=" + jwtToken);
                        })
                )
                .authenticationProvider(authentificationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        // Disable frame options for H2 console
        http.headers(headers -> headers.frameOptions(frame -> frame.disable()));
        return http.build();
    }

    @Bean
    public AuthenticationProvider authentificationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService.userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}