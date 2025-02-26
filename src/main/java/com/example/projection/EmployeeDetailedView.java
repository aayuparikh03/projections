package com.example.projection;

public interface EmployeeDetailedView {
    String getFirstName();
    String getEmail();
    default String getFullDetails() {
        return getFirstName() + " - " + getEmail();
    }
}
