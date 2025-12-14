package org.arin;

import lombok.*;

import java.util.ArrayList;

public class Assignment {
    private String assignmentId;
    private String assignmentName;
    private double weight;
    private int maxScore;
    private ArrayList<Integer> scores;

    private static int nextId = 1;
}
