package com.bridgelabz.assignment.controller;


import com.bridgelabz.assignment.dto.EmployeePayrollDto;
import com.bridgelabz.assignment.model.EmployeePayroll;
import com.bridgelabz.assignment.service.EmployeePayrollService;
import com.bridgelabz.assignment.utility.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employeepayroll")
public class EmployeePayrollController
{
    @Autowired
    private EmployeePayrollService employeeService;

    @PostMapping("/save")
    public Response save(@RequestBody EmployeePayrollDto employeePayrollDto)
    {
        return employeeService.saveEmployee(employeePayrollDto);
    }

    @GetMapping("/get/{id}")
    public EmployeePayrollDto getEmployeePayroll(@PathVariable int id)
    {
        return employeeService.getPayrollById(id);
    }

    @GetMapping("/getall")
    public List<EmployeePayrollDto> getAllEmployeePayroll()
    {
        return employeeService.getAll();
    }

    @PostMapping("/savelist")
    public String saveList(@RequestBody List<EmployeePayrollDto> productDtos)
    {
        employeeService.saveAll(productDtos);
        return "List of Employees Saved";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEmployeePayroll(@PathVariable int id)
    {
        return employeeService.deletePayrollById(id);
    }


    @PutMapping("/update/{id}")
    public String updateEmployeePayroll(@PathVariable int id, @RequestBody EmployeePayrollDto employeePayrollDto)
    {
        employeeService.updateEmployeePayroll(employeePayrollDto,id);
        return "Updated Successfully";
    }
}
