package com.bridgelabz.assignment.service;

import com.bridgelabz.assignment.dto.EmployeePayrollDto;
import com.bridgelabz.assignment.exception.PayrollException;
import com.bridgelabz.assignment.mapper.EmployeePayrollMapper;
import com.bridgelabz.assignment.model.EmployeePayroll;
import com.bridgelabz.assignment.repository.EmployeePayrollRepository;
import com.bridgelabz.assignment.utility.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

/*
    EmployeePayrollService class annotated with service to declare this class as service
 */
@Service
public class EmployeePayrollService
{
    @Autowired
    private EmployeePayrollRepository employeePayrollRepository;

    @Autowired
    private EmployeePayrollMapper employeeMapper;

    public Response saveEmployee(EmployeePayrollDto employeePayrollDto)
    {
        employeePayrollRepository.findByEmail(employeePayrollDto.getEmail()).ifPresent(employeePayroll -> {
            throw new PayrollException("Employee Payroll Email is Already Present ");
        });
        employeePayrollRepository.save(employeeMapper.dtoToModel(employeePayrollDto));
        return new Response("Employee Payroll Added", HttpStatus.OK);
    }

    public EmployeePayrollDto getPayrollById(int id)
    {
        return employeeMapper.modelToDto(employeePayrollRepository.findById(id)
                .orElseThrow(() -> new PayrollException("Employee Payroll Not Found for ID :" + id)));
    }


    public List<EmployeePayrollDto> getAll() {
        return employeeMapper.modelsToDtos(employeePayrollRepository.findAll());
    }

    public List<EmployeePayroll> saveAll(List<EmployeePayrollDto> productDtos)
    {
        return employeePayrollRepository.saveAll(employeeMapper.dtoToModels(productDtos));
    }

    public String deletePayrollById(int id) {
        employeePayrollRepository.deleteById(id);
        return "Employee Deleted Successfully";
    }


    public EmployeePayroll updateEmployeePayroll(@RequestBody EmployeePayrollDto employeePayrollDto,int id)
    {
        EmployeePayroll oldData = null;
        Optional<EmployeePayroll> optional = Optional.ofNullable(employeePayrollRepository.findById(id).orElseThrow(() -> new PayrollException("User with ID" + id + " Cannot Be Updated Because It's not Present in the Payroll List")));
        if (optional.isPresent())
            oldData = optional.get();
            oldData.setGender(employeePayrollDto.getGender());
            oldData.setDepartment(employeePayrollDto.getDepartment());
            oldData.setAddress(employeePayrollDto.getAddress());
            oldData.setSalary(employeePayrollDto.getSalary());
            oldData.setName(employeePayrollDto.getName());
            oldData.setPhoneNumber(employeePayrollDto.getPhoneNumber());
            return employeePayrollRepository.save(oldData);
    }
}
