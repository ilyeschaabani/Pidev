package com.example.evaluationmicroservicee.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;



@Document(collection = "User")
public class User  {

    @Id
    String idUser;
    String nom;
    String prenom;
    String email;
    String password;

    String telephone;
    String adresse;

    // Gamification fields
    int totalPoints = 0;
    int level = 1;
    List<String> badges = new ArrayList<>();

    @DBRef
    List<AttemptedQuizRecords> quizAttempts = new ArrayList<>();

    @DBRef
    List<Gamification> gamifications = new ArrayList<>();
    @DBRef
    List<QuizResult> quizResults = new ArrayList<>();
    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<String> getBadges() {
        return badges;
    }

    public void setBadges(List<String> badges) {
        this.badges = badges;
    }

    public List<AttemptedQuizRecords> getQuizAttempts() {
        return quizAttempts;
    }

    public void setQuizAttempts(List<AttemptedQuizRecords> quizAttempts) {
        this.quizAttempts = quizAttempts;
    }

    public List<Gamification> getGamifications() {
        return gamifications;
    }

    public void setGamifications(List<Gamification> gamifications) {
        this.gamifications = gamifications;
    }
}
