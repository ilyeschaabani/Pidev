package tn.esprit.authenticationmicroservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.authenticationmicroservice.Config.SecurityConfig;
import tn.esprit.authenticationmicroservice.Entity.ForgotPassword;
import tn.esprit.authenticationmicroservice.Entity.User;
import tn.esprit.authenticationmicroservice.Repository.ForgotPasswordRepository;
import tn.esprit.authenticationmicroservice.Repository.UserRepository;
import tn.esprit.authenticationmicroservice.Service.EMail.EmailService;
import tn.esprit.authenticationmicroservice.Service.User.UserService;
import tn.esprit.authenticationmicroservice.dto.ChangePassword;
import tn.esprit.authenticationmicroservice.dto.Mailbody;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

@RestController
@RequestMapping("/forgotPassword")
public class ForgotPasswordController {
    @Autowired
    private UserService userService;

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final ForgotPasswordRepository forgotPasswordRepository;
    private  final SecurityConfig securityConfig;

    public ForgotPasswordController(UserRepository userRepository, EmailService emailService, ForgotPasswordRepository forgotPasswordRepository, SecurityConfig securityConfig) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.forgotPasswordRepository = forgotPasswordRepository;
        this.securityConfig = securityConfig;
    }
    @PostMapping("/verifyMail/{email}")
    public ResponseEntity<String> verifyEmail (@PathVariable String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
        int otp = OTPGererator();
        Mailbody mailbody = Mailbody.builder()
                .to(email)
                .text("this is your verification code"+ otp)
                .subject("Verification code")
                .build();

        ForgotPassword fp = new ForgotPassword().builder()
                .otp(otp)
                .expirationTime(new Date(System.currentTimeMillis() + 70*1000))
                .user(user)
                .build();
        emailService.sendSimpleMessage(mailbody);
        forgotPasswordRepository.save(fp);
        return ResponseEntity.ok("Verification code sent");

    }

    @PostMapping("/verifyOTP/{otp}/{email}")
    public ResponseEntity<String> verifyOTP (@PathVariable Integer otp, @PathVariable String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        ForgotPassword fp =   forgotPasswordRepository.findByOtpAndUser(otp, user)
                .orElseThrow(() -> new RuntimeException("Invalid OTP or User not found"+email));
        if (fp.getExpirationTime().before(Date.from(Instant.now()))) {
            forgotPasswordRepository.deleteById(fp.getFpid());
            return new ResponseEntity<>("OTP expired", HttpStatus.EXPECTATION_FAILED)   ;
        }
        return ResponseEntity.ok("OTP verified");
    }

        @PostMapping("/changePassword/{email}")
        public ResponseEntity<String> changePasswordHandler (@RequestBody ChangePassword changePassword, @PathVariable String email) {

            if (!Objects.equals(changePassword.password(), changePassword.rpeatPassword())) {
                return new ResponseEntity<>("passwords do not match", HttpStatus.EXPECTATION_FAILED);
            }

            String encodedPassword = securityConfig.passwordEncoder().encode(changePassword.password());
            userService.updatePassword(email, encodedPassword);

            return ResponseEntity.ok("password changed successfully");

        }

    private Integer OTPGererator() {
        Random random = new Random();
        return random.nextInt(100_000, 999_999);
    }

}
