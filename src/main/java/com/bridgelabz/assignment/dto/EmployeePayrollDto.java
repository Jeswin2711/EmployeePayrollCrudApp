package com.bridgelabz.assignment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;


/*
A DTO class for the Details to be Displayed to the Client
 */
@Getter
@Setter
public class EmployeePayrollDto
{
    private String name;
    private Character gender;
    private String address;
    private long phoneNumber;
    private int salary;
    private String email;
    private String department;
}
