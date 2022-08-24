package com.bridgelabz.assignment.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
ExceptionsHandler class is used to handle Exceptions
 */

@ControllerAdvice
public class ExceptionsHandler
    {

        @ExceptionHandler(PayrollException.class)
        public ResponseEntity<ExceptionDetails> userExceptionHandler(PayrollException ex) {
            ExceptionDetails message = new ExceptionDetails(
                    new Date(),
                    ex.getMessage(),
                    HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }


        @ExceptionHandler(Exception.class)
        public ResponseEntity<ExceptionDetails> handlePayrollNotFoundException(Exception e) {
            ExceptionDetails exception = new ExceptionDetails(new Date(), e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }