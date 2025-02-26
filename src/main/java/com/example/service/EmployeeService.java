package com.example.service;

import com.example.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public <T> List<T> getEmployeesBySalary(Double salary, Class<T> type) {
        return employeeRepository.findBySalaryGreaterThan(salary, type);
    }
}
