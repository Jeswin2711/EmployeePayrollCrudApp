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
        employeeService.saveEmployee(employeePayrollDto);
        Response response = new Response("Employee Payroll Saved" , HttpStatus.OK);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


    /*
    Function to Save a List of Employee Payroll
     */
    @PostMapping("/savelist")
    public ResponseEntity<Response> saveAll(@Valid @RequestBody List<EmployeePayrollDto> employeePayrollDto)
    {
        employeeService.saveAll(employeePayrollDto);
        Response response = new Response("Employee Payroll List Saved" , HttpStatus.OK);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


    /*
    Function to get the employeepayroll by Id from Repository by Calling Service Class
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<EmployeePayrollDto> getEmployeePayroll(@PathVariable int id)
    {
        EmployeePayrollDto getEmployeePayrollDto = employeeService.getPayrollById(id);
        return new ResponseEntity<>(getEmployeePayrollDto,HttpStatus.ACCEPTED);
    }

    /*
    Function to get the Employeepayroll list from Repository by Calling Service
     */
    @GetMapping("/getall")
    public ResponseEntity<List<EmployeePayrollDto>> getAllEmployeePayroll()
    {
        List<EmployeePayrollDto> employeePayrollDtosList = employeeService.getAll();
        return new ResponseEntity<>(employeePayrollDtosList,HttpStatus.ACCEPTED);
    }

    /*
    Function to update the Employeepayroll by ID from Repository by Calling Service
     */
    @PostMapping("/savelist")
    public ResponseEntity<Response> saveList(@RequestBody List<EmployeePayrollDto> productDtos)
    {
        employeeService.saveAll(productDtos);
        Response response = new Response("Employee Payroll List Saved" , HttpStatus.OK);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    /*
    Function to delete the Employee payroll from Repository by Calling Service
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteEmployeePayroll(@PathVariable int id)
    {
        employeeService.deletePayrollById(id);
        Response response = new Response("Employee Payroll Deleted", HttpStatus.OK );
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    /*
    Function to update the Employee payroll in the Repository by Calling Service Class
     */

    @PutMapping("/update/{id}")
    public ResponseEntity<Response> updateEmployeePayroll(@PathVariable int id, @RequestBody EmployeePayrollDto employeePayrollDto)
    {
        employeeService.updateEmployeePayroll(employeePayrollDto,id);
        Response response = new Response("Employee Payroll Updated for Id :"+id, HttpStatus.OK );
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
