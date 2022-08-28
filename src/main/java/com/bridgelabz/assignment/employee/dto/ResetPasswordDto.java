package com.bridgelabz.assignment.employee.dto;


import lombok.Data;

@Data
public class ResetPasswordDto
{
    private String oldPassWord;
    private String newPassWord;
}
