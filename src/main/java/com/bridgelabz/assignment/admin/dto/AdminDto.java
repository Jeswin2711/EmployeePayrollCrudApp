package com.bridgelabz.assignment.admin.dto;


import com.bridgelabz.assignment.employee.model.EmployeePayroll;
import lombok.Data;
import java.util.List;

@Data
/*
Admin Dto
 */
public class AdminDto
{
    private String userName;

    private List<EmployeePayroll> employeePayrolls;
}
