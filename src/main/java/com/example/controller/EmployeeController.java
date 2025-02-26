package com.example.controller;

import com.example.dto.EmployeeDTO;
import com.example.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;


    @GetMapping
    public List<EmployeeDTO> getEmployees(@RequestParam("salary") Double salary) {
        return employeeService.getEmployees(salary);
    }
}
