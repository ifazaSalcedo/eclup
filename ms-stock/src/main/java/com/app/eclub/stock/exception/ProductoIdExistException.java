package com.app.eclub.stock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ProductoIdExistException extends RuntimeException{

    private String message;

    public ProductoIdExistException(String message){
        super(message);
    }

}
