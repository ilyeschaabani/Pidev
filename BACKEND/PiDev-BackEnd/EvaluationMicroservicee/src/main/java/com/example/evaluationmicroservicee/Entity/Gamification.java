package com.example.evaluationmicroservicee.Entity;

import com.example.evaluationmicroservicee.Entity.Enumeration.Defi;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Gamification")
public class Gamification {
    private int pts;
    private int niveau;
    private String badge;
    private Defi defi;
    private Evaluation idEvaluation;

    // Constructeur privé
    public Gamification(int pts, int niveau, String badge, Defi defi, Evaluation idEvaluation) {
        this.pts = pts;
        this.niveau = niveau;
        this.badge = badge;
        this.defi = defi;
        this.idEvaluation = idEvaluation;
    }

    public static Builder builder() {
        return new Builder();
    }
    // Getter et Setter (si nécessaire)
    public int getPts() {
        return pts;
    }

    public void setPts(int pts) {
        this.pts = pts;
    }

    // Autres getters et setters ...

    // Builder
    public static class Builder {
        private int pts;
        private int niveau;
        private String badge;
        private Defi defi;
        private Evaluation idEvaluation;

        public Builder pts(int pts) {
            this.pts = pts;
            return this;
        }

        public Builder niveau(int niveau) {
            this.niveau = niveau;
            return this;
        }

        public Builder badge(String badge) {
            this.badge = badge;
            return this;
        }

        public Builder defi(Defi defi) {
            this.defi = defi;
            return this;
        }

        public Builder idEvaluation(Evaluation idEvaluation) {
            this.idEvaluation = idEvaluation;
            return this;
        }

        public Gamification build() {
            return new Gamification(pts, niveau, badge, defi, idEvaluation);
        }


    }
}
