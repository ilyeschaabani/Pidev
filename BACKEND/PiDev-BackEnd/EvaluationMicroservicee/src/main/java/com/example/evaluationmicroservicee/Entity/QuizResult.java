package com.example.evaluationmicroservicee.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Document(collection = "quiz_results")
@Data
public class QuizResult {
    @Id
    private String id;

    private String idEvaluation;
    private int score;
    private Date completionDate;
    private String userId; // ou @DBRef User user;
    private List<UserAnswer> answers;

    private int percentage;
    private LocalDateTime dateTaken;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getEvaluationId() {
        return idEvaluation;
    }

    public void setEvaluationId(String evaluationId) {
        this.idEvaluation = evaluationId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }


    public String getIdEvaluation() {
        return idEvaluation;
    }

    public void setIdEvaluation(String idEvaluation) {
        this.idEvaluation = idEvaluation;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<UserAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<UserAnswer> answers) {
        this.answers = answers;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public LocalDateTime getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(LocalDateTime dateTaken) {
        this.dateTaken = dateTaken;
    }
}