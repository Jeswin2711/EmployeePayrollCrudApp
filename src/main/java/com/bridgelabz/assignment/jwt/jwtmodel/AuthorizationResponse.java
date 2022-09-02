package com.bridgelabz.assignment.jwt.jwtmodel;


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
