package tn.esprit.projectmicroservice.Entity.Enumeration;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum StatutProjet {
    EN_ATTENTE,
    EN_COURS,
    TERMINE,
    REJETE // Add this value

    }
