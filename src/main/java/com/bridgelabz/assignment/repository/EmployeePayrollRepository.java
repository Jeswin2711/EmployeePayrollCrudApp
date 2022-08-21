package com.bridgelabz.assignment.repository;

import com.bridgelabz.assignment.model.EmployeePayroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeePayrollRepository extends JpaRepository<EmployeePayroll,Integer> 
{
    Optional<EmployeePayroll> findByEmail(String email);
}
