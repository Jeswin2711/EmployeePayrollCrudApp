package com.bridgelabz.assignment.controller;


import com.bridgelabz.assignment.dto.EmployeePayrollDto;
import com.bridgelabz.assignment.model.EmployeePayroll;
import com.bridgelabz.assignment.service.EmployeePayrollService;
import com.bridgelabz.assignment.utility.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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


    @GetMapping
    public String welcomeMessage()
    {
        return "Welcome to Employee Payroll Program";
    }

    /*
    Function to save a Single Employee Payroll
     */
    @PostMapping("/save")
    public ResponseEntity<Response> save(@Valid @RequestBody EmployeePayrollDto employeePayrollDto)
    {
        return new ResponseEntity<>(new Response("Employee Payroll Saved",employeeService.saveEmployee(employeePayrollDto)),HttpStatus.OK);
    }


    /*
    Function to get the employeepayroll by Id from Repository by Calling Service Class
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getEmployeePayroll(@PathVariable int id)
    {
        return new ResponseEntity<>(new Response("The Data for " + id,employeeService.getPayrollById(id)),HttpStatus.OK);
    }

    /*
    Function to get the Employeepayroll list from Repository by Calling Service
     */
    @GetMapping("/getall")
    public ResponseEntity<Response> getAllEmployeePayroll()
    {
        return new ResponseEntity<>(new Response("Employee Payroll List",employeeService.getAll()),HttpStatus.OK);
    }

    /*
    Function to update the Employeepayroll by ID from Repository by Calling Service
     */
    @PostMapping("/savelist")
    public ResponseEntity<Response> saveList(@RequestBody List<EmployeePayrollDto> productDtos)
    {
        return new ResponseEntity<>(new Response("Employee List Saved",employeeService.saveAll(productDtos)),HttpStatus.OK);
    }

    /*
    Function to delete the Employee payroll from Repository by Calling Service
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteEmployeePayroll(@PathVariable int id)
    {
        return new ResponseEntity<>(new Response("Employee Payroll Deleted", employeeService.deletePayrollById(id)),HttpStatus.OK);
    }

    /*
    Function to update the Employee payroll in the Repository by Calling Service Class
     */

    @PutMapping("/update/{id}")
    public ResponseEntity<Response> updateEmployeePayroll(@PathVariable int id, @RequestBody EmployeePayrollDto employeePayrollDto)
    {
        return new ResponseEntity<>(new Response("Employee Payroll Updated for Id :"+id,employeeService.updateEmployeePayroll(employeePayrollDto,id)),HttpStatus.OK);
    }
}
