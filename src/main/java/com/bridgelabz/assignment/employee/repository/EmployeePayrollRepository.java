package com.bridgelabz.assignment.employee.repository;

import com.bridgelabz.assignment.employee.model.EmployeePayroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/*
    EmployeePayrollRepository class for DB management
 */
@Repository
public interface EmployeePayrollRepository extends JpaRepository<EmployeePayroll,Integer> 
{
    Optional<EmployeePayroll> findByEmail(String email);

    Optional<EmployeePayroll> findByPassWord(String passWord);

    Optional<EmployeePayroll> findByUserName(String userName);

}
