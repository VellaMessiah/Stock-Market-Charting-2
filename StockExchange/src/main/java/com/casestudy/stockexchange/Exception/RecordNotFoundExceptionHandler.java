package com.casestudy.stockexchange.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RecordNotFoundExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleRecordNotFoundException(RecordNotFoundException e){
        ErrorResponse errorResponse = new ErrorResponse("No such Record found", HttpStatus.NOT_FOUND.value(),System.currentTimeMillis());
        return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
    }
}
