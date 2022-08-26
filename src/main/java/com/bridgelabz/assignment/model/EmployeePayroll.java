package com.bridgelabz.assignment.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/*
A Model for Employee Payroll
 */

@Getter
@Setter
@Entity
@Table(name = "Payroll")
@ToString
public class EmployeePayroll
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private int id;
    @Column(name = "Name")
    private String name;
    @Column(name = "Date")
    @JsonFormat(pattern = "yyyy/mm/dd")
    private Date createdAt;
    @Column(name = "Salary")
    private int salary;
    @Column(name = "Address")
    private String address;
    @Column(name = "PhoneNumber")
    private long phoneNumber;
    @Column(name = "Department")
    private String department;
    @Column(name = "BasicPay")
    private int basic_pay;
    @Column(name = "Deductions")
    private int deductions;
    @Column(name = "TaxPay")
    private int taxPay;
    @Column(name = "IncomePay")
    private int incomeTax;
    @Column(name = "NetPay")
    private int netPay;
    @Column(name = "Gender")
    private Character gender;
    @Column(name = "Email")
    private String email;
    @Column(name = "UserName")
    private String userName;
    @Column(name = "PassWord")
    private String passWord;
}