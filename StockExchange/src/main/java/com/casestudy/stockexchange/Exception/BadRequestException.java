package com.casestudy.stockexchange.Exception;

public class BadRequestException extends Throwable{
    private String message;

    public BadRequestException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage(){
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
