package com.bridgelabz.assignment.admin.controller;


import com.bridgelabz.assignment.employee.dto.EmployeePayrollDto;
import com.bridgelabz.assignment.employee.model.EmployeePayroll;
import com.bridgelabz.assignment.employee.dto.ResetPasswordDto;
import com.bridgelabz.assignment.admin.service.AdminService;
import com.bridgelabz.assignment.utility.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/*
Controller Class
 */

@RestController
@RequestMapping("/admin")
public class AdminController
{
    @Autowired
    private AdminService adminService;

    /*
    Function to save the employee payroll to Repository by Calling Service
     */

    @GetMapping
    public String welcomeMessage()
    {
        return "Welcome to Employee Payroll Program";
    }

    /*
    Function to save Employee Payrolls in the Admin
     */
    @PostMapping("/employeepayroll/addList/{adminId}")
    public ResponseEntity<Response> save(@PathVariable int adminId , @RequestBody List<EmployeePayroll> employeePayroll)
    {
        return new ResponseEntity<>(adminService.saveEmployees(adminId,employeePayroll),HttpStatus.OK);
    }
    /*
    Function to get the employeepayroll by ID from Repository by Calling Service Class
     */
    @GetMapping("/employeepayroll/get/{id}")
    public ResponseEntity<Response> getAdminDetailsById(@PathVariable int id)
    {
        return new ResponseEntity<>(new Response("The Data for Admin ID : " + id , adminService.getAdminById(id)),HttpStatus.OK);
    }

    /*
    Function to get the Employee payroll list from Repository by Calling Service
     */
    @GetMapping("/employeepayroll/getall")
    public ResponseEntity<Response> getAllAdmins()
    {
        return new ResponseEntity<>(new Response("Admin List",adminService.getAll()),HttpStatus.OK);
    }

    /*
    Function to delete the Employee payroll from Repository by Calling Service
     */
    @DeleteMapping("/employeepayroll/delete/{id}")
    public ResponseEntity<Response> deleteAdminById(@PathVariable int id)
    {
        return new ResponseEntity<>(new Response("Deleting Admin", adminService.deleteAdmin(id)),HttpStatus.OK);
    }

    /*
    Function to update the Employee payroll in the Repository by Calling Service Class
     */

    @PutMapping("/employeepayroll/update/{adminId}/{id}")
    public ResponseEntity<Response> updateEmployeePayroll(@PathVariable int adminId , @PathVariable int id, @RequestBody EmployeePayrollDto employeePayrollDto)
    {
        adminService.updateEmployeeDetail(adminId,id,employeePayrollDto);
        return new ResponseEntity<>(new Response("Employee Payroll Updated for Id : "+id,adminService.updateEmployeeDetail(adminId,id,employeePayrollDto)),HttpStatus.OK);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Response> resetAdminPassWord(@RequestBody ResetPasswordDto resetPasswordDto)
    {
        return new ResponseEntity<>(new Response("Password Reset Successfull " , adminService.resetPassWord(resetPasswordDto)),HttpStatus.OK);
    }

    @PostMapping("/login/{id}/{token}")
    public ResponseEntity<Response> loginAdmin(@PathVariable int id ,
                                               @PathVariable String token)
    {
        adminService.login(id,token);
        return new ResponseEntity<>(new Response("Login Successfull",HttpStatus.OK),HttpStatus.OK);
    }
}
