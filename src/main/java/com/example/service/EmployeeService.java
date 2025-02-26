package com.example.service;

import com.example.dto.EmployeeDTO;
import com.example.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@AllArgsConstructor
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public List<EmployeeDTO> getEmployees(Double salary) {
        return employeeRepository.findBySalaryGreaterThan(salary);
    }
}
