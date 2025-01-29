package com.aerazo.clientes.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {

        BindingResult result = ex.getBindingResult();

        List<String> errors = new ArrayList<>();

        for (FieldError fieldError : result.getFieldErrors()) {
            errors.add(fieldError.getDefaultMessage());
        }

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), errors);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {

        List<String> errors = new ArrayList<>();

        errors.add(ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), errors);

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(SaldoInsuficienteException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleSaldoInsuficienteException(SaldoInsuficienteException ex) {

        List<String> errors = new ArrayList<>();

        errors.add(ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(9999, errors);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }

}

