package com.example.dto;


public class EmployeeDTO {
    private String firstName;
    private String email;
    private Double salary;


    public EmployeeDTO(String firstName, String email, Double salary) {
        this.firstName = firstName;
        this.email = email;
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
