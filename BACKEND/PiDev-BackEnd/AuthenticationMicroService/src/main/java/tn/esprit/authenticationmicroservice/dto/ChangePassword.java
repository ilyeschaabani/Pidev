package tn.esprit.authenticationmicroservice.dto;

import lombok.Builder;

@Builder
public record ChangePassword(String password,String rpeatPassword) {
}
