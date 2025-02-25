package com.example.controller;

import com.example.projection.EmployeeProjection;
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
    public List<EmployeeProjection> getEmployees(@RequestParam("salary") Double salary) {
        return employeeService.getEmployees(salary);
    }
}
