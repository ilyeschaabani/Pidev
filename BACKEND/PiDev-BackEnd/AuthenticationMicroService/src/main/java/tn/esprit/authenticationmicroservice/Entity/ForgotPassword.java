package tn.esprit.authenticationmicroservice.Entity;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "ForgetPassword")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ForgotPassword {
    @Id
    String fpid;
    Integer otp;
    Date expirationTime;
    User user;
}
