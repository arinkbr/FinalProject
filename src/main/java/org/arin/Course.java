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
}
