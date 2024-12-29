package com.app.eclub.ventas.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RabbitMqNotFountException extends RuntimeException{

    public RabbitMqNotFountException(String message) {
        super(message);
    }

}
