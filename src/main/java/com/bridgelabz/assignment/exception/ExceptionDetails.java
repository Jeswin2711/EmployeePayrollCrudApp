package com.bridgelabz.assignment.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/*
An Exception Model
 */
@Data
@AllArgsConstructor
public class ExceptionDetails
{
    private Date timeStamp;

    private String message;

    private int statusCode;
}
