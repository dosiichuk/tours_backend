package com.tours.backend.domain;

public enum UserRole {
    ADMIN("ADMIN"),
    USER("USER");

    private final String role;
    UserRole(String role) {
        this.role = role;
    }

    public String getRoleString() {
        return role;
    }

    public static UserRole fromString(String role) {
        for (UserRole userRole : UserRole.values()) {
            if (userRole.role.equalsIgnoreCase(role)) {
                return userRole;
            }
        }
        throw new IllegalArgumentException("No constant with role " + role + " found");
    }
}
