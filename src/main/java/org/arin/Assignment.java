package org.arin;

import lombok.*;

import java.util.ArrayList;

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
}
