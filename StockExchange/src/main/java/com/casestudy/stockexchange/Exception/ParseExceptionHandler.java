package com.casestudy.stockexchange.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.ParseException;

@ControllerAdvice
public class ParseExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleParseException(ParseException e){
        ErrorResponse errorResponse = new ErrorResponse("Could not Parse Date or time please ensure format (dd-mm-yyyy; hh:mm:ss) :"+e.getMessage(), HttpStatus.NOT_FOUND.value(),System.currentTimeMillis());
        return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.EXPECTATION_FAILED);
    }
}
