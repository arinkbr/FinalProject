package org.arin;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
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
        this.studentName = Util.toTitleCase(studentName);
        this.gender = gender;
        this.address = address;
        this.department = department;
        this.registeredCourses = new ArrayList<Course>();
    }

    /**
     * Adds the course to the student's list AND adds the student to the course list.
     * @param course the course to register
     * @return true if success, false if null or already registered
     */
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

    /**
     * Removes the course from the student's list AND removes the student from the course list.
     * @param course the course to drop
     * @return true if success, false if null or not registered
     */
    public boolean dropCourse(Course course) {
        if (course == null) return false;
        if (!registeredCourses.contains(course)) return false;

        registeredCourses.remove(course);
        course.getRegisteredStudents().remove(this);

        return true;
    }

    /**
     * Returns a simplified student string containing only: studentId, studentName, departmentName.
     * @return simplified student info line
     */
    public String toSimplifiedString() {
        String depName = (department == null) ? "None" : department.getDepartmentName();
        return studentId + " | " + studentName + " | " + depName;
    }

    @Override
    public String toString() {
        String depName = (department == null) ? "None" : department.getDepartmentName();

        String result = "";
        result += "Student ID: " + studentId + "\n";
        result += "Student Name: " + studentName + "\n";
        result += "Gender: " + gender + "\n";
        result += "Address: " + address + "\n";
        result += "Department: " + depName + "\n";

        result += "Registered Courses:\n";
        for (Course c : registeredCourses) {
            String cDepName = (c.getDepartment() == null) ? "None" : c.getDepartment().getDepartmentName();
            result += "  " + c.getCourseId() + " - " + c.getCourseName() + " - " + cDepName + "\n";
        }

        return result;
    }

    /**
     * Sets student name in Title Case
     * @param studentName new name
     */
    public void setStudentName(String studentName) {
        this.studentName = Util.toTitleCase(studentName);
    }
}
