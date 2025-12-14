package org.arin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProjectTest {

    @Test
    @DisplayName("toTitleCase: \"computer science\" -> \"Computer Science\"")
    void toTitleCase1() {
        String input = "computer science";
        String expected = "Computer Science";
        String actual = Util.toTitleCase(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("toTitleCase: \"yI wAnG\" -> \"Yi Wang\"")
    void toTitleCase2() {
        String input = "yI wAnG";
        String expected = "Yi Wang";
        String actual = Util.toTitleCase(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("isPostalCodeValid: A1B2C3 -> true")
    void postalCodeValid1() {
        String postalCode = "A1B2C3";
        boolean expected = true;
        boolean actual = Address.isPostalCodeValid(postalCode);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("isPostalCodeValid: 123456 -> false")
    void postalCodeValid2() {
        String postalCode = "123456";
        boolean expected = false;
        boolean actual = Address.isPostalCodeValid(postalCode);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("isPostalCodeValid: A1B2 -> false")
    void postalCodeValid3() {
        String postalCode = "A1B2";
        boolean expected = false;
        boolean actual = Address.isPostalCodeValid(postalCode);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("isDepartmentNameValid: \"Computer Science\" -> true")
    void departmentNameValid1() {
        String name = "Computer Science";
        boolean expected = true;
        boolean actual = Department.isDepartmentNameValid(name);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("isDepartmentNameValid: \"Math123\" -> false")
    void departmentNameValid2() {
        String name = "Math123";
        boolean expected = false;
        boolean actual = Department.isDepartmentNameValid(name);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("isAssignmentWeightValid: total weight = 100 -> true")
    void assignmentWeightValid1() {
        Department d = new Department("Computer Science");
        Course c = new Course("Programming", 3.0, d);

        c.addAssignment("Midterm", 40, 100);
        c.addAssignment("Final", 60, 100);

        boolean expected = true;
        boolean actual = c.isAssignmentWeightValid();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("isAssignmentWeightValid: total weight != 100 -> false")
    void assignmentWeightValid2() {
        Department d = new Department("Computer Science");
        Course c = new Course("Programming", 3.0, d);

        c.addAssignment("Midterm", 30, 100);
        c.addAssignment("Final", 50, 100);

        boolean expected = false;
        boolean actual = c.isAssignmentWeightValid();
        Assertions.assertEquals(expected, actual);
    }
}
