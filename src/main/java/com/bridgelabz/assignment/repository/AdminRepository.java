package com.bridgelabz.assignment.repository;

import com.bridgelabz.assignment.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>
{
    Optional<Admin> findByUserName(String userName);

}
