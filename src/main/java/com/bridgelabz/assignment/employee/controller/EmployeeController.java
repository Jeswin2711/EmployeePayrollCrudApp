package com.bridgelabz.assignment.employee.controller;


import com.bridgelabz.assignment.employee.dto.EmployeeAuthenticationDto;
import com.bridgelabz.assignment.employee.service.EmployeeService;
import com.bridgelabz.assignment.exception.CustomException;
import com.bridgelabz.assignment.employee.model.EmployeePayroll;
import com.bridgelabz.assignment.employee.dto.ResetPasswordDto;
import com.bridgelabz.assignment.employee.repository.EmployeePayrollRepository;
import com.bridgelabz.assignment.utility.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;



@RestController
@RequestMapping("/employee")
public class EmployeeController
{
    @Autowired
    private EmployeeService employeeService;

    /*
        Function to Login a User
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody EmployeeAuthenticationDto employeeAuthenticationDto)
    {
        return new ResponseEntity<>(employeeService.loginEmployee(employeeAuthenticationDto),HttpStatus.OK);
    }

    /*
        To reset a Password
     */
    @PostMapping("/reset-password/{id}")
    public ResponseEntity<Response> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto, @PathVariable int id)
    {
        return new ResponseEntity<>(employeeService.resetPassWord(resetPasswordDto,id),HttpStatus.OK);
    }
}
