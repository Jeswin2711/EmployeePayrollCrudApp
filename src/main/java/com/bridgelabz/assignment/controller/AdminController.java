package com.bridgelabz.assignment.controller;


import com.bridgelabz.assignment.dto.EmployeePayrollDto;
import com.bridgelabz.assignment.exception.CustomException;
import com.bridgelabz.assignment.model.Admin;
import com.bridgelabz.assignment.model.EmployeePayroll;
import com.bridgelabz.assignment.repository.AdminRepository;
import com.bridgelabz.assignment.repository.EmployeePayrollRepository;
import com.bridgelabz.assignment.service.EmployeePayrollService;
import com.bridgelabz.assignment.utility.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/*
Controller Class
 */
@RestController
@RequestMapping("/admin")
public class AdminController
{
    @Autowired
    private EmployeePayrollService employeeService;

    /*
    Function to save the employeepayroll to Repository by Calling Service
     */
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private EmployeePayrollRepository repository;

    @GetMapping
    public String welcomeMessage()
    {
        return "Welcome to Employee Payroll Program";
    }
    /*
    Function to save a Single Employee Payroll
     */
    @PostMapping("/employeepayroll/save")
    public ResponseEntity<Response> save(@Valid @RequestBody Admin admin)
    {
        adminRepository.findByUserName(admin.getUserName()).ifPresent(
                action -> {
                    throw new CustomException("Admin Username Already Present Use /add/{userName}");
                }
        );
        return new ResponseEntity<>(new Response("Employee Payroll Saved",adminRepository.save(admin)),HttpStatus.OK);
    }

    @PostMapping("/employeepayroll/addList/{adminId}")
    public void addList(@PathVariable int adminId , @RequestBody List<EmployeePayroll> employeePayroll)
    {
        adminRepository.findById(adminId)
                .map(
                        admin -> {
                            admin.setId(adminId);
                            admin.getEmployeePayrolls().addAll(employeePayroll);
                            repository.saveAll(employeePayroll);
                            return admin;
                        }
                ).orElseThrow(() -> {throw new CustomException("Admin Not Found");});
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
