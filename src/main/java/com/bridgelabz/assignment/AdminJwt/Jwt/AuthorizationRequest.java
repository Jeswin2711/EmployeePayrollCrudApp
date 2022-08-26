package com.bridgelabz.assignment.AdminJwt.Jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/*
    A model for AuthorizationRequest
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizationRequest {

    private String userName;

    private String passWord;
}
