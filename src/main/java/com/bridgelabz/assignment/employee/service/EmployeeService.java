package com.bridgelabz.assignment.employee.service;

import com.bridgelabz.assignment.employee.dto.EmployeeAuthenticationDto;
import com.bridgelabz.assignment.employee.dto.ResetPasswordDto;
import com.bridgelabz.assignment.employee.model.EmployeePayroll;
import com.bridgelabz.assignment.employee.repository.EmployeePayrollRepository;
import com.bridgelabz.assignment.exception.CustomException;
import com.bridgelabz.assignment.utility.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class EmployeeService
{
    @Autowired
    private EmployeePayrollRepository employeePayrollRepository;

    public Response loginEmployee(@RequestBody EmployeeAuthenticationDto employeeAuthenticationDto)
    {
        employeePayrollRepository.findByEmail(employeeAuthenticationDto.getUserName())
                .orElseThrow(() -> {
                    throw new CustomException("You are Not Authorized");
                });
        employeePayrollRepository.findByPassWord(employeeAuthenticationDto.getPassWord())
                .orElseThrow(() -> {
                    throw new CustomException("You are Not Authorized");
                });
        return new Response("Login Successfull", HttpStatus.OK);
    }

    public Response resetPassWord(ResetPasswordDto resetPasswordDto)
    {
        EmployeePayroll oldData = null;
        Optional<EmployeePayroll> optional = employeePayrollRepository.findByPassWord(resetPasswordDto.getOldPassWord());
        if (optional.isPresent()) {
            oldData = optional.get();
            oldData.setPassWord(resetPasswordDto.getNewPassWord());
            employeePayrollRepository.save(oldData);
        }
        else
        {
            throw new CustomException("User Not Found");
        }

        return new Response("Password Reset Successfull",HttpStatus.OK);
    }
}
