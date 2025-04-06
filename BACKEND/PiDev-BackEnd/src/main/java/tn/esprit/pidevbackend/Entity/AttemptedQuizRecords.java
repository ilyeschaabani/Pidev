package tn.esprit.pidevbackend.Entity;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Getter
@Document(collection = "attempted_records")
public class AttemptedQuizRecords {

    @Id
    private String aId;

    private double obtainedMarks;
    private int attemptedQuestions;
    private int correctAttempted;
    private Date date;

    @DBRef
    private Evaluation evaluation;

    private String evaluationId;
    public AttemptedQuizRecords() {}

    public String getaId() {
        return aId;
    }

    public void setaId(String aId) {
        this.aId = aId;
    }

    public void setObtainedMarks(double obtainedMarks) {
        this.obtainedMarks = obtainedMarks;
    }

    public void setAttemptedQuestions(int attemptedQuestions) {
        this.attemptedQuestions = attemptedQuestions;
    }

    public void setCorrectAttempted(int correctAttempted) {
        this.correctAttempted = correctAttempted;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }
}