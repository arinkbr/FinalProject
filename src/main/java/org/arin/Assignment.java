package org.arin;

import lombok.*;

import java.util.ArrayList;
import java.util.Random;

public class Assignment {
    private String assignmentId;
    private String assignmentName;
    private double weight;
    private ArrayList<Integer> scores;

    private static int nextId = 1;
    private double assignmentAvg;

    public Assignment(String assignmentName, double weight, int maxScore, int numOfStudent) {
        this.assignmentId = String.format("A%03d", nextId++);
        this.assignmentName = assignmentName;
        this.weight = weight;
        this.scores = new ArrayList<>();

        for (int i = 0; i < numOfStudent; i++) {
            scores.add(null);
        }

        this.assignmentAvg = 0.0;
    }

    public void calcAssignmentAvg() {
        int sum = 0;

        for (Integer score : scores) {
            if (score != null) {
                sum += score;
            }
        }

        if (scores.isEmpty()) {
            assignmentAvg = 0;
        } else {
            assignmentAvg = sum / (double) scores.size();
        }
    }

    public void generateRandomScore() {
        Random rand = new Random();

        for (int i = 0; i < scores.size(); i++) {
            int roll = rand.nextInt(11); // 0 to 10
            int score;

            if (roll == 0) {
                score = rand.nextInt(60);
            } else if (roll <= 2) {
                score = 60 + rand.nextInt(10);
            } else if (roll <= 4) {
                score = 70 + rand.nextInt(10);
            } else if (roll <= 8) {
                score = 80 + rand.nextInt(10);
            } else {
                score = 90 + rand.nextInt(11);
            }

            scores.set(i, score);
        }
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "assignmentId='" + assignmentId + '\'' +
                ", assignmentName='" + assignmentName + '\'' +
                ", weight=" + weight +
                '}';
    }
}
