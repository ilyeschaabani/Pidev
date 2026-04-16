package tn.esprit.forummicroservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.forummicroservice.Entity.Report;
import tn.esprit.forummicroservice.Entity.ReportStatus;
import tn.esprit.forummicroservice.Service.ReportService;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    // 🔹 Ajouter un report
    @PostMapping
    public Report createReport(@RequestBody Report report) {
        return reportService.createReport(report);
    }

    // 🔹 Récupérer un report par ID
    @GetMapping("/{id}")
    public Report getReportById(@PathVariable String id) {
        return reportService.getReportById(id);
    }

    // 🔹 Récupérer tous les reports
    @GetMapping
    public List<Report> getAllReports() {
        return reportService.getAllReports();
    }

    // 🔹 Récupérer les reports par statut
    @GetMapping("/status/{status}")
    public List<Report> getReportsByStatus(@PathVariable String status) {
        return reportService.getReportsByStatus(status);
    }

    // 🔹 Mettre à jour le statut d’un report
    @PutMapping("/{id}/status")
    public Report updateReportStatus(@PathVariable String id, @RequestParam ReportStatus status) {
        return reportService.updateReportStatus(id, status);
    }

    // 🔹 Supprimer un report par ID
    @DeleteMapping("/{id}")
    public void deleteReport(@PathVariable String id) {
        reportService.deleteReport(id);
    }
}
