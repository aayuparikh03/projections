package com.example.projection;

import org.springframework.beans.factory.annotation.Value;

public interface EmployeeProjection {
    String getFirstName();
    String getEmail();
    Double getSalary();

    @Value("#{target.firstName + ' ' + target.lastName}")
    String getFullName();
}
