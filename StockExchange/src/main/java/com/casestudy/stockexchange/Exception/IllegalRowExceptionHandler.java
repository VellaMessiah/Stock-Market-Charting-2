package com.casestudy.stockexchange.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class IllegalRowExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> IllegalRowExceptionHandler(IllegalRowException e){
        ErrorResponse errorResponse = new ErrorResponse("Illegal Row Exception :"+e.getMessage(), HttpStatus.NOT_FOUND.value(),System.currentTimeMillis());
        return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.EXPECTATION_FAILED);
    }
}
