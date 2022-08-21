package com.bridgelabz.assignment.controller;


import com.bridgelabz.assignment.dto.EmployeePayrollDto;
import com.bridgelabz.assignment.model.EmployeePayroll;
import com.bridgelabz.assignment.service.EmployeePayrollService;
import com.bridgelabz.assignment.utility.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
Controller Class
 */
@RestController
@RequestMapping("/employeepayroll")
public class EmployeePayrollController
{
    @Autowired
    private EmployeePayrollService employeeService;

    /*
    Function to save the employeepayroll to Repository by Calling Service
     */
    @PostMapping("/save")
    public Response save(@RequestBody EmployeePayrollDto employeePayrollDto)
    {
        return employeeService.saveEmployee(employeePayrollDto);
    }

    /*
    Function to get the employeepayroll by Id from Repository by Calling Service Class
     */
    @GetMapping("/get/{id}")
    public EmployeePayrollDto getEmployeePayroll(@PathVariable int id)
    {
        return employeeService.getPayrollById(id);
    }

    /*
    Function to get the Employeepayroll list from Repository by Calling Service
     */
    @GetMapping("/getall")
    public List<EmployeePayrollDto> getAllEmployeePayroll()
    {
        return employeeService.getAll();
    }

    /*
    Function to update the Employeepayroll by ID from Repository by Calling Service
     */
    @PostMapping("/savelist")
    public String saveList(@RequestBody List<EmployeePayrollDto> productDtos)
    {
        employeeService.saveAll(productDtos);
        return "List of Employees Saved";
    }

    /*
    Function to delete the Employee payroll from Repository by Calling Service
     */
    @DeleteMapping("/delete/{id}")
    public String deleteEmployeePayroll(@PathVariable int id)
    {
        return employeeService.deletePayrollById(id);
    }

    /*
    Function to update the Employee payroll in the Repository by Calling Service Class
     */

    @PutMapping("/update/{id}")
    public String updateEmployeePayroll(@PathVariable int id, @RequestBody EmployeePayrollDto employeePayrollDto)
    {
        employeeService.updateEmployeePayroll(employeePayrollDto,id);
        return "Updated Successfully";
    }
}
