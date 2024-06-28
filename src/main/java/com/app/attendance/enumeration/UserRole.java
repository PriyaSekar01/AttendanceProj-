package com.app.attendance.enumeration;

public enum UserRole {
	ADMIN, EMPLOYEE;
	  public static UserRole fromString(String value) {
        for (UserRole role : UserRole.values()) {
            if (role.name().equalsIgnoreCase(value)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid role: " + value);
    }
}
