package com.example.evaluationmicroservicee.Entity;

import java.util.List;

public class ResultatEvaluation {
    private String idEvaluation;
    private int score;
    private int totalQuestions;
    private List<Reponse> reponsesCorrectes;
    private List<Reponse> reponsesUtilisateur;

    // Getters et Setters
    public String getIdEvaluation() {
        return idEvaluation;
    }

    public void setIdEvaluation(String idEvaluation) {
        this.idEvaluation = idEvaluation;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public List<Reponse> getReponsesCorrectes() {
        return reponsesCorrectes;
    }

    public void setReponsesCorrectes(List<Reponse> reponsesCorrectes) {
        this.reponsesCorrectes = reponsesCorrectes;
    }

    public List<Reponse> getReponsesUtilisateur() {
        return reponsesUtilisateur;
    }

    public void setReponsesUtilisateur(List<Reponse> reponsesUtilisateur) {
        this.reponsesUtilisateur = reponsesUtilisateur;
    }
}