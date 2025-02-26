package com.example.repository;

import com.example.dto.EmployeeDTO;
import com.example.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    @Query("SELECT new com.example.dto.EmployeeDTO(e.firstName, e.email, e.salary) FROM Employee e WHERE e.salary > :salary")
    List<EmployeeDTO> findBySalaryGreaterThan(@Param("salary") Double salary);
}
