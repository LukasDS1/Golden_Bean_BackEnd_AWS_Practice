package com.github.coffee_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.github.coffee_service.dto.ErrorResponseDto;
import com.github.coffee_service.exception.CoffeeNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CoffeeNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleCoffeeNotFound(
        CoffeeNotFoundException ex,
        HttpServletRequest request
    ) {
        ErrorResponseDto error = new ErrorResponseDto(
            ex.getMessage(),
            HttpStatus.NOT_FOUND.value(),
            System.currentTimeMillis(),
            request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGeneral(
        Exception ex,
        HttpServletRequest request
    ) {
        ErrorResponseDto error = new ErrorResponseDto(
            ex.getMessage(),
            HttpStatus.BAD_REQUEST.value(),
            System.currentTimeMillis(),
            request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}
