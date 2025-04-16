package tn.esprit.projectmicroservice.Entity.Enumeration;


public enum TaskStatus {
    TO_DO("À faire"),
    IN_PROGRESS("En cours"),
    UNDER_REVIEW("En revue"),
    COMPLETED("Terminé"),
    BLOCKED("Bloqué");

    private final String displayName;

    TaskStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

