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

    public Department(String departmentName) {

        if (isDepartmentNameValid(departmentName)) {
            this.departmentId = String.format("D%02d", nextId);
            this.departmentName = departmentName;
            nextId++;
        } else {
            this.departmentId = null;
            this.departmentName = null;
        }
    }

    public void setDepartmentName(String departmentName) {
        if (isDepartmentNameValid(departmentName)) {
            this.departmentName = departmentName;
        }
    }
}
