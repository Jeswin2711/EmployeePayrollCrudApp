package com.bridgelabz.assignment.employee.controller;


import com.bridgelabz.assignment.employee.dto.AuthenticationDto;
import com.bridgelabz.assignment.employee.service.EmployeeService;
import com.bridgelabz.assignment.employee.dto.ResetPasswordDto;
import com.bridgelabz.assignment.utility.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    public ResponseEntity<?> login(@RequestBody AuthenticationDto authenticationDto)
    {
        return new ResponseEntity<>(employeeService.loginEmployee(authenticationDto),HttpStatus.OK);
    }

    /*
        To reset a Password
     */
    @PostMapping("/reset-password/{id}")
    public ResponseEntity<Response> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto, @PathVariable int id)
    {
        return new ResponseEntity<>(employeeService.resetPassWord(resetPasswordDto,id),HttpStatus.OK);
    }

    @PostMapping("/forgot-password/{id}")
    public ResponseEntity<Response> forgotPassword(@PathVariable int id)
    {
        return new ResponseEntity<>(employeeService.forgotPassWord(id),HttpStatus.OK);
    }
}
