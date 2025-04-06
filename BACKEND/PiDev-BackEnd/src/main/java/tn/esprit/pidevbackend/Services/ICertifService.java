package tn.esprit.pidevbackend.Services;

import tn.esprit.pidevbackend.Entity.Certif;

import java.util.List;

public interface ICertifService {
    Certif addCertif(Certif certif);
    Certif updateCertif(String id, Certif certif);
    void deleteCertif(String id);
    Certif getCertifById(String id);
    List<Certif> getAllCertifs();
}
