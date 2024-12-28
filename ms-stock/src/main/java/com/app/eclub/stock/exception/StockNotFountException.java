package com.app.eclub.stock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class StockNotFountException extends RuntimeException{

    private String message;
    
    public StockNotFountException(String message){
        super(message);
    }

}
