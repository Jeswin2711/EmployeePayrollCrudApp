package com.bridgelabz.assignment.admin.model;


import com.bridgelabz.assignment.employee.model.EmployeePayroll;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "Admin")
@AllArgsConstructor
@NoArgsConstructor
/*
A Model Class for Admin
 */
public class Admin
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private int id;
    private String userName;
    private String passWord;
    private String token;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_admin_id" , referencedColumnName = "admin_id")
    private List<EmployeePayroll> employeePayrolls;

    public Admin(String userName, String passWord ,String token) {
        this.userName = userName;
        this.passWord = passWord;
        this.token = token;
    }
}
