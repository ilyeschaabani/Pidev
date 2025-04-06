package tn.esprit.pidevbackend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Document(collection = "evaluations")
public class Evaluation {
    @Id
    private String idEvaluation;
    private String titre;
    private int note;
    private  String description;
    private String maxMarks;
    private String noOfQuestions;
    private boolean active = false;
    private Long quesId;

    public Evaluation() {
    }

    @DBRef(lazy = true)
    @JsonIgnore
    private Set<AttemptedQuizRecords> quizRecords = new HashSet<>();

    // Dans Evaluation.java
    @DBRef(lazy = true)
    private Set<Question> questions = new HashSet<>();

    public String getIdEvaluation() {
        return idEvaluation;
    }

    public void setIdEvaluation(String idEvaluation) {
        this.idEvaluation = idEvaluation;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaxMarks() {
        return maxMarks;
    }

    public void setMaxMarks(String maxMarks) {
        this.maxMarks = maxMarks;
    }

    public String getNoOfQuestions() {
        return noOfQuestions;
    }

    public void setNoOfQuestions(String noOfQuestions) {
        this.noOfQuestions = noOfQuestions;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getQuesId() {
        return quesId;
    }

    public void setQuesId(Long quesId) {
        this.quesId = quesId;
    }

    public Set<AttemptedQuizRecords> getQuizRecords() {
        return quizRecords;
    }

    public void setQuizRecords(Set<AttemptedQuizRecords> quizRecords) {
        this.quizRecords = quizRecords;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }
}