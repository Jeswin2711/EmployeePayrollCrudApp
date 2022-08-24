package com.bridgelabz.assignment.utility;


import lombok.AllArgsConstructor;
import lombok.Data;

/*
Response Model
 */
@Data
@AllArgsConstructor
public class Response
{
    private String message;

    private Object data;
}
