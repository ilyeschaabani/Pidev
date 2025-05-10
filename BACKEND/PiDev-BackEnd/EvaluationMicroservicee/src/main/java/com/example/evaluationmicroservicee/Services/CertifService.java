package com.example.evaluationmicroservicee.Services;

import com.example.evaluationmicroservicee.Entity.Certif;
import com.example.evaluationmicroservicee.Repository.CertifRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CertifService implements ICertifService {

    @Autowired
    private CertifRepository certifRepository;

    @Override
    public Certif addCertif(Certif certif) {
        return certifRepository.save(certif);
    }

    @Override
    public Certif updateCertif(String id, Certif certif) {
        if (certifRepository.existsById(id)) {
            certif.setIdCertif(id);
            return certifRepository.save(certif);
        } else {
            throw new RuntimeException("Certification not found");
        }
    }

    @Override
    public void deleteCertif(String id) {
        certifRepository.deleteById(id);
    }

    @Override
    public Certif getCertifById(String id) {
        return certifRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Certification not found"));
    }

    @Override
    public List<Certif> getAllCertifs() {
        return certifRepository.findAll();
    }

    // Génération du certificat en PDF
    public byte[] generateCertifPdf(Certif certif) throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        PdfWriter.getInstance(document, outputStream);
        document.open();

        // Contenu du certificat
        document.add(new Paragraph("🏆 CERTIFICAT DE RÉUSSITE 🏆"));
        document.add(new Paragraph(" "));
        document.add(new Paragraph("Formation ID : " + certif.getIdFormation()));
        document.add(new Paragraph("Étudiant ID : " + certif.getIdEtudiant()));
        document.add(new Paragraph("Félicitations ! Vous avez réussi le quiz avec succès."));
        document.add(new Paragraph(" "));
        document.add(new Paragraph("Date d'émission : " + certif.getDateObtention()));

        document.close();
        return outputStream.toByteArray();
    }
}
