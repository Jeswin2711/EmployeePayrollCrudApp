package com.bridgelabz.assignment.employee.service;

import com.bridgelabz.assignment.sendmail.MailSenderImpl;
import com.bridgelabz.assignment.employee.dto.EmployeeAuthenticationDto;
import com.bridgelabz.assignment.employee.dto.ResetPasswordDto;
import com.bridgelabz.assignment.employee.model.EmployeePayroll;
import com.bridgelabz.assignment.employee.repository.EmployeePayrollRepository;
import com.bridgelabz.assignment.exception.CustomException;
import com.bridgelabz.assignment.utility.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class EmployeeService
{
    @Autowired
    private EmployeePayrollRepository employeePayrollRepository;

    @Autowired
    private MailSenderImpl mailSender;

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

    public Response resetPassWord(ResetPasswordDto resetPasswordDto,int id)
    {
        EmployeePayroll oldData = null;
        if(employeePayrollRepository.findById(id).isPresent())
        {
            EmployeePayroll employee = employeePayrollRepository.findById(id).get();
            Optional<EmployeePayroll> optional = employeePayrollRepository.findByPassWord(resetPasswordDto.getOldPassWord());
            if (optional.isPresent()) {
                if(employee.getPassWord().equals(resetPasswordDto.getOldPassWord()))
                {
                    oldData = optional.get();
                    oldData.setPassWord(resetPasswordDto.getNewPassWord());
                    employeePayrollRepository.save(oldData);
                    mailSender.sendResetPassWordMailToEmployee(optional.get().getId());
                }
                else
                {
                    throw new CustomException("The OldPassword is Invalid");
                }
            }
            else
            {
                throw new CustomException("No User Found With That Password");
            }
        }
        else
        {
            throw new CustomException("User Not Found");
        }
        return new Response("Password Reset Successfull",HttpStatus.OK);
    }


    public Response forgotPassWord(int id)
    {
        UUID randomUUID = UUID.randomUUID();
        String randomPassWord = randomUUID.toString().replaceAll("_", "");
        employeePayrollRepository.findById(id)
                        .ifPresent(
                                action ->
                                {
                                    action.setPassWord(randomPassWord);
                                    employeePayrollRepository.save(action);
                                    mailSender.sendForgotPassWordMailToEmployee(action.getId(),randomPassWord);
                                }
                        );
        System.out.println(randomPassWord);
        return new Response("Forgot Password Request Accepted",HttpStatus.OK);
    }
}
