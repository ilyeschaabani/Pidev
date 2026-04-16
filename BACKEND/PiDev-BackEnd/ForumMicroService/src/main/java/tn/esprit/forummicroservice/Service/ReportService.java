package tn.esprit.forummicroservice.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.forummicroservice.Entity.Report;
import tn.esprit.forummicroservice.Entity.ReportStatus;
import tn.esprit.forummicroservice.Repository.ReportRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    // 🔹 Ajouter un report
    public Report createReport(Report report) {
        report.setStatus(ReportStatus.WAITING); // Toujours en attente au départ
        return reportRepository.save(report);
    }

    // 🔹 Récupérer un report par ID
    public Report getReportById(String id) {
        return reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report non trouvé avec l'ID: " + id));
    }

    // 🔹 Récupérer tous les reports
    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    // 🔹 Filtrer les reports par statut
    public List<Report> getReportsByStatus(String status) {
        return reportRepository.findByStatus(status);
    }

    // 🔹 Mettre à jour le statut d’un report
    public Report updateReportStatus(String id, ReportStatus newStatus) {
        Optional<Report> reportOptional = reportRepository.findById(id);
        if (reportOptional.isPresent()) {
            Report report = reportOptional.get();
            report.setStatus(newStatus);
            return reportRepository.save(report);
        } else {
            throw new RuntimeException("Report non trouvé avec l'ID: " + id);
        }
    }

    // 🔹 Supprimer un report par ID
    public void deleteReport(String id) {
        if (!reportRepository.existsById(id)) {
            throw new RuntimeException("Report non trouvé avec l'ID: " + id);
        }
        reportRepository.deleteById(id);
    }
}
