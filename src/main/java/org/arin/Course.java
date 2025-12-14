package org.arin;

import lombok.*;

import java.util.ArrayList;


public class Course {
    private String courseId;
    private String courseName;
    private double credits;
    private Department department;
    private ArrayList<Assignment> assignments;
    private ArrayList<Student> registeredStudents;

    private static int nextId = 1;

    private ArrayList<Integer> finalScores;

    public Course(String courseName, double credits, Department department) {
        this.courseName = Util.toTitleCase(courseName);
        this.credits = credits;
        this.department = department;

        String depId;
        if (department == null) {
            depId = "D00";
        } else {
            depId = department.getDepartmentId();
        }

        String twoDigitCourseId = String.format("%02d", nextId++);

        this.courseId = "C-" + depId + "-" + twoDigitCourseId;

        this.assignments = new ArrayList<>();
        this.registeredStudents = new ArrayList<>();
        this.finalScores = new ArrayList<>();
    }

    public boolean isAssignmentWeightValid() {
        double sum = 0;

        for (Assignment assignment : assignments) {
            sum += assignment.getWeight();
        }

        return sum == 100;
    }

    public boolean registerStudent(Student student) {
        if (student == null) return false;
        if (registeredStudents.contains(student))
            return false;

        registeredStudents.add(student);

        for (Assignment a : assignments) {
            a.getScores().add(null);
        }

        finalScores.add(null);

        return true;
    }

    public int[] calcStudentsAverage() {
        int size = registeredStudents.size();
        int[] averages = new int[size];

        for (int i = 0; i < size; i++) {
            double total = 0;

            for (Assignment a : assignments) {
                Integer score = a.getScores().get(i);
                if (score == null) score = 0;

                total += score * (a.getWeight() / 100.0);
            }

            averages[i] = (int) total;
        }

        return averages;
    }
}
