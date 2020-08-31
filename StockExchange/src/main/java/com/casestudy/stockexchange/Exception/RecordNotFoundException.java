package com.casestudy.stockexchange.Exception;

public class RecordNotFoundException extends Throwable {
    private String message;

    public RecordNotFoundException(String message) {
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
