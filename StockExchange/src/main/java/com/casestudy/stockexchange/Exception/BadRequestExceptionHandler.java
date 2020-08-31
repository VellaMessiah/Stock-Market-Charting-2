package com.casestudy.stockexchange.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BadRequestExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException e){
        ErrorResponse errorResponse = new ErrorResponse("BAD request or INVALID INPUT" + e.getMessage(), HttpStatus.NOT_FOUND.value(),System.currentTimeMillis());
        return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.BAD_REQUEST);

    }
}
