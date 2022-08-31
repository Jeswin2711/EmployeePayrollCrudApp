package com.bridgelabz.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.Date;

/*
ExceptionsHandler class is used to handle Exceptions
 */

@ControllerAdvice
public class ExceptionsHandler
    {
        /*
        To Handle Exception for Custom Exception
         */

        @ExceptionHandler(CustomException.class)
        public ResponseEntity<ExceptionDetails> userExceptionHandler(CustomException ex) {
            ExceptionDetails message = new ExceptionDetails(
                    new Date(),
                    ex.getMessage(),
                    HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }

        /*
        Handling Global Exception
         */

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ExceptionDetails> handlePayrollNotFoundException(Exception e) {
            ExceptionDetails exception = new ExceptionDetails(new Date(), e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }