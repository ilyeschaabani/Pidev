package tn.esprit.authenticationmicroservice.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Consultant")
@RequiredArgsConstructor
public class ConsultantController {
    @GetMapping
    public ResponseEntity<String> getConsultant(){
        return ResponseEntity.ok("Hello Consultant");
    }
}
