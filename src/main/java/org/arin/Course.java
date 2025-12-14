package org.arin;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@EqualsAndHashCode
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

    public boolean addAssignment(String assignmentName, double weight, int maxScore) {
        if (assignmentName == null) {
            return false;
        }

        if (weight <= 0) {
            return false;
        }

        Assignment assignment = new Assignment(Util.toTitleCase(assignmentName),
                weight,
                maxScore,
                registeredStudents.size());

        assignments.add(assignment);
        return true;
    }

    public void generateScores() {
        for (Assignment a : assignments) {
            a.generateRandomScore();
        }

        int[] averages = calcStudentsAverage();
        for (int i = 0; i < averages.length; i++) {
            finalScores.set(i, averages[i]);
        }
    }

    public void displayScores() {
        System.out.println("Course: " + courseName + "(" + courseId + ")");

        System.out.printf("%-25s", "");
        for (Assignment a : assignments) {
            System.out.printf("%-15s", a.getAssignmentName());
        }
        System.out.printf("%-15s%n", "Final Score");

        for (int i = 0; i < registeredStudents.size(); i++) {
            Student s = registeredStudents.get(i);
            System.out.printf("%-25s", s.getStudentName());

            for (Assignment a : assignments) {
                Integer score = a.getScores().get(i);
                System.out.printf("%-15s", score == null ? "-" : score);
            }

            System.out.printf("%-15s%n", finalScores.get(i));
        }

        System.out.printf("%-25s", "Average");
        for (Assignment a : assignments) {
            a.calcAssignmentAvg();
            System.out.printf("%-15d", (int) a.getAssignmentAvg());
        }
        System.out.println();
    }

    public String toSimplifiedString() {
        String depName = (department == null) ? "None" : department.getDepartmentName();
        return courseId + " | " + courseName + " | " + credits + " | " + depName;
    }

    @Override
    public String toString() {
        String depName = (department == null) ? "None" : department.getDepartmentName();

        String result = "";
        result += "Course ID: " + courseId + "\n";
        result += "Course Name: " + courseName + "\n";
        result += "Credits: " + credits + "\n";
        result += "Department: " + depName + "\n";

        result += "Assignments:\n";
        for (Assignment a : assignments) {
            result += "  " + a + "\n";
        }

        result += "Registered Students:\n";
        for (Student s : registeredStudents) {
            result += "  " + s.getStudentId() + " - "
                    + s.getStudentName() + " - "
                    + depName + "\n";
        }

        result += "Assignment weights valid? " + isAssignmentWeightValid();

        return result;
    }

    public void setCourseName(String courseName) {
        this.courseName = Util.toTitleCase(courseName);
    }
}
