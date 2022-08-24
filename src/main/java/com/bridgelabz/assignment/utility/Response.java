package com.bridgelabz.assignment.utility;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
Response Model
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response
{
    private String message;

    private Object data;
}
