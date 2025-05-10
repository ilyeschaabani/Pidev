package com.example.evaluationmicroservicee.Services;

import com.example.evaluationmicroservicee.Entity.Certif;
import com.itextpdf.text.DocumentException;

import java.util.List;

public interface ICertifService {
    Certif addCertif(Certif certif);
    Certif updateCertif(String id, Certif certif);
    void deleteCertif(String id);
    Certif getCertifById(String id);
    List<Certif> getAllCertifs();

     byte[] generateCertifPdf(Certif certif) throws DocumentException;
}
