package com.bridgelabz.assignment.admin.security;

import com.bridgelabz.assignment.exception.CustomException;
import lombok.Data;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/*
    MyUserDetailsEmployee implementing the Interface UserDetailsService
    which helps us to Configure the User Details
 */
@Service
@Data
public class AdminDetails implements UserDetailsService {

    private String userName;

    private String passWord;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username.equals(userName))
        {
            return new User(username,passWord,new ArrayList<>());
        }
        else
        {
            throw new CustomException("Invalid Credentials");
        }
    }
}
