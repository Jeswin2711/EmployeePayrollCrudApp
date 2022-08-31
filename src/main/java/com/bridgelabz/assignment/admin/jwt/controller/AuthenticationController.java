package com.bridgelabz.assignment.admin.jwt.controller;

import com.bridgelabz.assignment.admin.jwt.jwtmodel.AuthorizationRequest;
import com.bridgelabz.assignment.admin.jwt.jwtmodel.AuthorizationResponse;
import com.bridgelabz.assignment.admin.jwt.jwtservice.JwtUtils;
import com.bridgelabz.assignment.admin.model.Admin;
import com.bridgelabz.assignment.admin.repository.AdminRepository;
import com.bridgelabz.assignment.admin.security.AdminDetails;
import com.bridgelabz.assignment.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/*
    A controller for Jwt
 */
@RestController
public class AuthenticationController
{

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AdminDetails userDetailsService;

    @Autowired
    private JwtUtils jwt;

    /*
        this endpoint helps us to return the token by Username and Password
     */
    @RequestMapping(value = "/admin/authenticate",method = RequestMethod.POST)
    public ResponseEntity<AuthorizationResponse> createAuthentication(@RequestBody AuthorizationRequest authorizationRequest) throws Exception {
        try {
            adminRepository.findByUserName(authorizationRequest.getUserName()).ifPresent(
                    action -> {
                        throw new CustomException("Admin Already Present");
                    }
            );
            userDetailsService.setUserName(authorizationRequest.getUserName());
            userDetailsService.setPassWord(authorizationRequest.getPassWord());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authorizationRequest.getUserName(),
                    authorizationRequest.getPassWord()
            ));
        }
        catch (BadCredentialsException e)
        {
            throw new Exception("Invalid Credentials");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(
                authorizationRequest.getUserName()
        );

        String jwtToken = jwt.generateToken(userDetails);

        adminRepository.save(
                new Admin(
                        userDetailsService.getUserName(),
                        userDetailsService.getPassWord()
                )
        );

        return ResponseEntity.ok(new AuthorizationResponse(jwtToken));
    }
}

