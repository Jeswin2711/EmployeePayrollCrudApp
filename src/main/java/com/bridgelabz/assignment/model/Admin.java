package com.bridgelabz.assignment.model;


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
    @GeneratedValue
    @Column(name = "admin_id")
    private int id;

    private String userName;

    public Admin(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    private String passWord;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_emp_id" , referencedColumnName = "admin_id")
    private List<EmployeePayroll> employeePayrolls;
}
