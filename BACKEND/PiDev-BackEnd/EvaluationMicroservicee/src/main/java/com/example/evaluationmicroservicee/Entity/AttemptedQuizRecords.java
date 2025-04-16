package com.example.evaluationmicroservicee.Entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;


@Document(collection = "attempted_records")
public class AttemptedQuizRecords {

    @Id
    private String aId;

    private double obtainedMarks;
    private int totalMarks;
    private int attemptedQuestions;
    private int correctAttempted;
    private Date date;

    @DBRef
    private Evaluation evaluation; // Relation à l'entité Evaluation

    private String idEvaluation; // Optionnel, utilisé si nécessaire pour stocker l'ID de l'évaluation
    private List<Reponse> responses;
    private Date completionDate;
    // Constructeur par défaut
    public AttemptedQuizRecords() {}

    // Constructeur complet pour faciliter la création de nouveaux enregistrements
    public AttemptedQuizRecords(double obtainedMarks, int attemptedQuestions, int correctAttempted, Date date, Evaluation evaluation) {
        this.obtainedMarks = obtainedMarks;
        this.attemptedQuestions = attemptedQuestions;
        this.correctAttempted = correctAttempted;
        this.date = date;
        this.evaluation = evaluation;
        if (evaluation != null) {
            this.idEvaluation = evaluation.getIdEvaluation(); // Assure que Evaluation a un `getId()`
        }
    }

    public String getaId() {
        return aId;
    }

    public void setaId(String aId) {
        this.aId = aId;
    }

    public double getObtainedMarks() {
        return obtainedMarks;
    }

    public void setObtainedMarks(double obtainedMarks) {
        this.obtainedMarks = obtainedMarks;
    }

    public int getAttemptedQuestions() {
        return attemptedQuestions;
    }

    public void setAttemptedQuestions(int attemptedQuestions) {
        this.attemptedQuestions = attemptedQuestions;
    }

    public int getCorrectAttempted() {
        return correctAttempted;
    }

    public void setCorrectAttempted(int correctAttempted) {
        this.correctAttempted = correctAttempted;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public String getEvaluationId() {
        return idEvaluation;
    }

    public void setEvaluationId(String evaluationId) {
        this.idEvaluation = evaluationId;
    }
}
