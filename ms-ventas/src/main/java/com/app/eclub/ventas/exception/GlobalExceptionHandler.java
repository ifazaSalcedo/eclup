package com.app.eclub.ventas.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
       
        @ExceptionHandler(StockNotFountException.class)
        public ResponseEntity<ErrorDetails> handlerStockNotFountException(StockNotFountException exception, WebRequest webRequest){
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
             exception.getMessage(),
              webRequest.getDescription(false)
              , "NO_STOCK");

              return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
        }
        
        @ExceptionHandler(RabbitMqNotFountException.class)
        public ResponseEntity<ErrorDetails> handlerRabbitMqNotFountExceptio(RabbitMqNotFountException exception, WebRequest webRequest){
            ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
             exception.getMessage(),
              webRequest.getDescription(false)
              , "BROKER_ERROR_COMMUNICATION");

              return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
        }

        @Override
        protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                HttpHeaders headers, HttpStatusCode status, WebRequest request) {

            Map<String,String> errors = new HashMap<>();
            List<ObjectError> errorList = ex.getBindingResult().getAllErrors();
            errorList.forEach(error -> {
                String fieldName= ((FieldError) error).getField();
                String message= error.getDefaultMessage();
                errors.put(fieldName, message);
            });
            
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
}
