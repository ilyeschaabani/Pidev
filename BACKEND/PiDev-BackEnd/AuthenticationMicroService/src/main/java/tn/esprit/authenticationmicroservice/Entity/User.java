package tn.esprit.authenticationmicroservice.Entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
//import tn.esprit.authentificationmicroservice.Entity.Enum.Role;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tn.esprit.authenticationmicroservice.Entity.Enum.Role;

import java.util.Collection;
import java.util.List;

@Document(collection = "users") // MongoDB collection name
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements UserDetails {

    @Id
    String idUser; // Use String for MongoDB ID

    @NotBlank
    String nom;

    @NotBlank
    String prenom;

    @Email
    @NotBlank
    String email;

    @NotBlank
    String password;

    @NotNull
    Role role;

    @NotBlank
    String telephone;

    @NotBlank
    String adresse;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
