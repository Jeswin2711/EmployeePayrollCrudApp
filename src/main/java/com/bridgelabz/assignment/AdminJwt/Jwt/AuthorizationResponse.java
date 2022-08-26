package com.bridgelabz.assignment.AdminJwt.Jwt;


import lombok.AllArgsConstructor;
import lombok.Getter;

/*
    A model for AuthorizationResponse
 */
@Getter
@AllArgsConstructor
public class AuthorizationResponse {

    private final String jwt;
}
