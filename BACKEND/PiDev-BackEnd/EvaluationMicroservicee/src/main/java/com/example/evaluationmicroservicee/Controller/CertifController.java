package com.example.evaluationmicroservicee.Controller;

import com.example.evaluationmicroservicee.Entity.Certif;
import com.example.evaluationmicroservicee.Services.CertifService;
import com.itextpdf.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/certif")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CertifController {
    @Autowired
    private CertifService certifService;

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


    @GetMapping("/download/{idCertif}")
    public ResponseEntity<ByteArrayResource> downloadCertif(@PathVariable String idCertif) throws DocumentException {
        // 1. Récupérer le certificat depuis la base (ou générer dynamiquement)
        Certif certif = certifService.getCertifById(idCertif);

        // 2. Générer le PDF (exemple simplifié)
        byte[] pdfBytes = certifService.generateCertifPdf(certif);

        // 3. Retourner le fichier pour téléchargement
        ByteArrayResource resource = new ByteArrayResource(pdfBytes);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=certificat_" + certif.getIdCertif() + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .contentLength(pdfBytes.length)
                .body(resource);
    }
}
