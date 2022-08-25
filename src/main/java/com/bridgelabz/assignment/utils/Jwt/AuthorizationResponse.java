package com.bridgelabz.assignment.utils.Jwt;


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
