package com.example.repository;

import com.example.entity.Employee;
import com.example.projection.EmployeeProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    @Query("""
        SELECT new com.example.projection.EmployeeProjection(e.firstName, e.email, e.salary, d.name)
        FROM Employee e
        JOIN e.department d
        WHERE e.salary > :salary
    """)
    List<EmployeeProjection> findBySalaryGreaterThan(@Param("salary") Double salary);
}
