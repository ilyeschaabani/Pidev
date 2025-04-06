package tn.esprit.pidevbackend.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidevbackend.Entity.Certif;
import tn.esprit.pidevbackend.Services.CertifService;

import java.util.List;

@RestController
@RequestMapping("/api/certif")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CertifController {
    @Autowired
    private  CertifService certifService;

    @PostMapping
    public Certif addCertif(@RequestBody Certif certif) {
        return certifService.addCertif(certif);
    }

    @PutMapping("/{id}")
    public Certif updateCertif(@PathVariable String id, @RequestBody Certif certif) {
        return certifService.updateCertif(id, certif);
    }

    @DeleteMapping("/{id}")
    public void deleteCertif(@PathVariable String id) {
        certifService.deleteCertif(id);
    }

    @GetMapping("/{id}")
    public Certif getCertifById(@PathVariable String id) {
        return certifService.getCertifById(id);
    }

    @GetMapping
    public List<Certif> getAllCertifs() {
        return certifService.getAllCertifs();
    }
}
