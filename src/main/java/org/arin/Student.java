package org.arin;

import lombok.*;

import java.util.ArrayList;

@Getter
@EqualsAndHashCode
public class Student {
    private String studentId;
    private String studentName;
    private Gender gender;
    private Address address;
    private Department department;
    private ArrayList<Course> registeredCourses;

    private static int nextId = 1;

    public enum Gender {
        MALE,
        FEMALE
    }

    public Student(String studentName, Gender gender, Address address, Department department) {
        this.studentId = String.format("S%06d", nextId++);
        this.studentName = studentName;
        this.gender = gender;
        this.address = address;
        this.department = department;
        this.registeredCourses = new ArrayList<Course>();
    }

    public boolean registerCourse(Course course) {
        if (course == null) {
            return false;
        }

        if (registeredCourses.contains(course)) {
            return false;
        }

        registeredCourses.add(course);
        course.registerStudent(this);

        return true;
    }

    public boolean dropCourse(Course course) {
        if (course == null) return false;
        if (!registeredCourses.contains(course)) return false;

        registeredCourses.remove(course);
        course.getRegisteredStudents().remove(this);

        return true;
    }

    public String toSimplifiedString() {
        String depName = (department == null) ? "None" : department.getDepartmentName();
        return studentId + " | " + studentName + " | " + depName;
    }

}
