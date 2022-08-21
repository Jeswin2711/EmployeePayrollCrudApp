package com.bridgelabz.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;


/*
ExceptionsHandler class is used to handle Exceptions
 */
@ControllerAdvice
public class ExceptionsHandler
    {

        @ExceptionHandler(PayrollException.class)
        public ResponseEntity<ExceptionDetails> userExceptionHandler(PayrollException ex, WebRequest request) {
            ExceptionDetails message = new ExceptionDetails(
                    new Date(),
                    ex.getMessage(),
                    HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<ExceptionDetails>(message, HttpStatus.NOT_FOUND);
        }


        @ExceptionHandler(Exception.class)
        public ResponseEntity<?> handlePayrollNotFoundException(Exception e) {
            ExceptionDetails exception = new ExceptionDetails(new Date(), e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }