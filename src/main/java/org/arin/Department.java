package org.arin;

import lombok.*;

public class Department {
    private String departmentId;
    private String departmentName;

    private static int nextId = 1;

    public static boolean isDepartmentNameValid(String departmentName) {
        if (departmentName == null || departmentName.isBlank()) {
            return false;
        }

        for (char c : departmentName.toCharArray()) {
            if (!Character.isLetter(c) && c != ' ') {
                return false;
            }
        }

        return true;
     }
}
