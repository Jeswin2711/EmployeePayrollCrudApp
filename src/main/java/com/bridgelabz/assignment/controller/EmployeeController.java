package com.bridgelabz.assignment.controller;


import com.bridgelabz.assignment.EmployeeAuthentication.EmployeeAuthentication;
import com.bridgelabz.assignment.exception.CustomException;
import com.bridgelabz.assignment.model.EmployeePayroll;
import com.bridgelabz.assignment.model.ResetPassword;
import com.bridgelabz.assignment.repository.AdminRepository;
import com.bridgelabz.assignment.repository.EmployeePayrollRepository;
import com.bridgelabz.assignment.utility.Response;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;



@RestController
@RequestMapping("/employee")
public class EmployeeController
{
    @Autowired
    private EmployeePayrollRepository employeeRepository;

    /*
        Function to Login a User
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody EmployeeAuthentication employeeAuthentication)
    {
        System.out.println("UserName"+employeeAuthentication.getUserName());
        System.out.println("passWord"+employeeAuthentication.getPassWord());
        employeeRepository.findByEmail("jeswin2711@gmail.com")
                        .ifPresent(employeePayroll ->
                        {
                            System.out.println("Present mf");
                        });
        employeeRepository.findByEmail(employeeAuthentication.getUserName())
                        .ifPresent(employeePayroll -> {
                            System.out.println("Present");
                        });
        System.out.println("--------------------");
        employeeRepository.findByEmail(employeeAuthentication.getUserName())
                .orElseThrow(() -> {
                    throw new CustomException("You are Not Authorized");
                });
        employeeRepository.findByPassWord(employeeAuthentication.getPassWord())
                .orElseThrow(() -> {
                    throw new CustomException("You are Not Authorized");
                });
        return new ResponseEntity<>(
                "Login Successfull", HttpStatus.OK);
    }

    /*
        To reset a Password
     */

    @PostMapping("/reset-password")
    public Response resetPassword(@RequestBody ResetPassword resetPassword)
    {
            EmployeePayroll oldData = null;
            Optional<EmployeePayroll> optional = employeeRepository.findByPassWord(resetPassword.getOldPassWord());
            if (optional.isPresent()) {
                oldData = optional.get();
                oldData.setPassWord(resetPassword.getNewPassWord());
                employeeRepository.save(oldData);
                return new Response("Password Reset Successful",HttpStatus.OK);
            }
            else
            {
                throw new CustomException("User Not Found");

            }
        }
}
