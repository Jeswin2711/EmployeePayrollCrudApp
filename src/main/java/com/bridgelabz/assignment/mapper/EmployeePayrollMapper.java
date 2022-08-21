package com.bridgelabz.assignment.mapper;


import com.bridgelabz.assignment.dto.EmployeePayrollDto;
import com.bridgelabz.assignment.model.EmployeePayroll;
import org.mapstruct.Mapper;

import java.util.List;

/*
Employee Payroll Mapper Interface that annotate @Mapper Annotation that provide auto implementation of the functions declared
 */
@Mapper(componentModel = "spring")
public interface EmployeePayrollMapper
{
    EmployeePayroll dtoToModel(EmployeePayrollDto employeePayrollDto);

    List<EmployeePayroll> dtoToModels(List<EmployeePayrollDto> employeePayrollDtos);

    EmployeePayrollDto modelToDto(EmployeePayroll employeePayroll);

    List<EmployeePayrollDto> modelsToDtos(List<EmployeePayroll> employeePayrolls);
}
