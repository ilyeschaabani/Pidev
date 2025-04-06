package tn.esprit.pidevbackend.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidevbackend.Entity.Certif;
import tn.esprit.pidevbackend.Repository.CertifRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CertifService implements ICertifService {
@Autowired
    private  CertifRepository certifRepository;

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
        return certifRepository.findById(id).orElseThrow(() -> new RuntimeException("Certification not found"));
    }

    @Override
    public List<Certif> getAllCertifs() {
        return certifRepository.findAll();
    }
}
