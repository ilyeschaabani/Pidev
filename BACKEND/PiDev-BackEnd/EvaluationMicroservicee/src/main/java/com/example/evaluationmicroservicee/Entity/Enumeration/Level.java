package com.example.evaluationmicroservicee.Entity.Enumeration;

public enum Level {

    BEGINNER(0),
    INTERMEDIATE(500),
    ADVANCED(1500),
    EXPERT(3000),
    MASTER(5000);

    private final int pointsRequired;

    Level(int pointsRequired) {
        this.pointsRequired = pointsRequired;
    }
    public int getPointsRequired() {
        return pointsRequired;
    }
}
