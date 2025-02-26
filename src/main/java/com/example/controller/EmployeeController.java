package com.example.controller;

import com.example.projection.EmployeeView;
import com.example.projection.EmployeeDetailedView;
import com.example.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<?> getEmployees(
            @RequestParam("salary") Double salary,
            @RequestParam("projectionType") String projectionType) {

        return switch (projectionType) {
            case "view" -> employeeService.getEmployeesBySalary(salary, EmployeeView.class);
            case "detailed" -> employeeService.getEmployeesBySalary(salary, EmployeeDetailedView.class);
           default -> throw new IllegalArgumentException("Invalid projection type");
        };
    }
}
