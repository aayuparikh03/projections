package com.example.repository;

import com.example.entity.Employee;
import com.example.projection.EmployeeProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    List<EmployeeProjection>findBySalaryGreaterThan(@Param("salary")
                                                    Double salary);
}
