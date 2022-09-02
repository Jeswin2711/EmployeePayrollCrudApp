package com.bridgelabz.assignment.admin.repository;

import com.bridgelabz.assignment.admin.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/*
    Admin Repository Class
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>
{
    Optional<Admin> findByUserName(String userName);

    Optional<Admin> findByPassWord(String passWord);

}
