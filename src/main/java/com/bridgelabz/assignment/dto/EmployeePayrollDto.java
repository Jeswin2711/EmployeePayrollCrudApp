package com.bridgelabz.assignment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


/*
A DTO class for the Details to be Displayed to the Client
 */
@Getter
@Setter
public class EmployeePayrollDto
{
    @NotEmpty(message = "The Name Should not be Empty")
    private String name;

    private Character gender;

    private String address;

    private long phoneNumber;

    private int salary;

    @NotEmpty
    @Email
    private String email;

    private String department;
}
