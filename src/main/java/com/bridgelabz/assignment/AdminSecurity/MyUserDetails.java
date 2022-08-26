package com.bridgelabz.assignment.AdminSecurity;

import com.bridgelabz.assignment.exception.CustomException;
import org.springframework.beans.factory.annotation.Value;
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
public class MyUserDetails implements UserDetailsService {

    @Value("${userDetails.userName}")
    private String userName;
    @Value("${userDetails.password}")
    String passWord;
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
