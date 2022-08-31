package com.bridgelabz.assignment.admin.service;

import com.bridgelabz.assignment.admin.dto.AdminDto;
import com.bridgelabz.assignment.mapper.AdminMapper;
import com.bridgelabz.assignment.admin.model.Admin;
import com.bridgelabz.assignment.admin.repository.AdminRepository;
import com.bridgelabz.assignment.employee.dto.EmployeePayrollDto;
import com.bridgelabz.assignment.employee.dto.ResetPasswordDto;
import com.bridgelabz.assignment.exception.CustomException;
import com.bridgelabz.assignment.mapper.EmployeePayrollMapper;
import com.bridgelabz.assignment.employee.model.EmployeePayroll;
import com.bridgelabz.assignment.employee.repository.EmployeePayrollRepository;
import com.bridgelabz.assignment.sendmail.MailSenderImpl;
import com.bridgelabz.assignment.utility.Response;
import com.bridgelabz.assignment.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

/*
    AdminService class annotated with service to declare this class as service
 */
@Service
public class AdminService
{
    @Autowired
    private EmployeePayrollMapper employeeMapper;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private EmployeePayrollRepository repository;

    @Autowired
    private MailSenderImpl mailSender;

    /*
        To Save A Employee Payroll
     */
    public Response saveEmployees(int adminId , List<EmployeePayroll> employeePayrolls)
    {
        adminRepository.findById(adminId)
                .map(
                        admin -> {
                            for(EmployeePayroll payroll : employeePayrolls)
                            {
                                repository.findByEmail(payroll.getEmail())
                                        .ifPresent(employeePayroll -> {
                                            throw new CustomException("Employee Payroll Email Already Present in the Admin ID :" + adminId);
                                        });
                                admin.getEmployeePayrolls().add(payroll);
                                repository.save(payroll);
                                mailSender.sendAuthMailToEmployee(payroll.getId());
                            }
                            return admin;
                        }
                ).orElseThrow(
                        () ->
                        {
                            throw new CustomException("Admin Not Found");
                        });
        return Utility.getResponse("Employee Payroll Added", HttpStatus.OK);
    }

    /*
        To Get A Employee Payroll
     */
    public AdminDto getAdminById(int id)
    {
        return adminMapper.modelToDto(adminRepository.findById(id).orElseThrow(
                () -> {throw new CustomException("Admin ID not found " + id);}
        ));
    }

    /*
        To Get List of Employee Payroll in Repository
     */

    public List<AdminDto> getAll() {
        return adminMapper.modelsToDtos(adminRepository.findAll());
    }

    /*
        To Delete A Employee Payroll in the Repository
     */
    public Response deleteAdmin(int id) {
        System.out.println("-______" + adminRepository.findAll());
        adminRepository.deleteById(id);
        return Utility.getResponse("Admin Deleted Successfully",HttpStatus.OK);
    }

    /*
        To Update A Employee Payroll
     */
    public Response updateEmployeeDetail(int adminId ,int id , @RequestBody EmployeePayrollDto employeePayrollDto)
    {
        getAdminById(adminId);
        EmployeePayrollDto oldData = null;
        Optional<EmployeePayroll> optional = Optional.ofNullable(repository.findById(id).orElseThrow(() -> new CustomException("User with ID :" + id + " Cannot Be Updated Because It's not Present in the Payroll List")));
        if (optional.isPresent())
            repository.save(employeeMapper.dtoToModel(employeeMapper.modelToDto(optional.get())));
        repository.findByEmail(employeePayrollDto.getEmail()).ifPresent(
                    employeePayroll -> {
                        throw new CustomException("User with Email ID exists");
                    }
            );
            return Utility.getResponse("Employee Payroll Updated Successfully" , HttpStatus.OK.value());
    }

    public Response resetPassWord(ResetPasswordDto resetPasswordDto)
    {
        Admin oldData = null;
        System.out.println("________"+adminRepository.findAll());
        Optional<Admin> optional = adminRepository.findByPassWord(resetPasswordDto.getOldPassWord());
        if (optional.isPresent()) {
            oldData = optional.get();
            oldData.setPassWord(resetPasswordDto.getNewPassWord());
            adminRepository.save(oldData);
            return new Response("Password Reset Successful",HttpStatus.OK);
        }
        else
        {
            throw new CustomException("User Not Found");
        }
    }

    public Response login(String username , String password)
    {
        Admin adminDetails = adminRepository.findByUserName(username).get();
        if(adminDetails.getPassWord().equals(password))
        {
                System.out.println("-------");
        }
        else
        {
            throw new CustomException("Admin UserName Password Not Found");
        }
        return new Response("Login Successfull for Admin",HttpStatus.OK);
    }
}