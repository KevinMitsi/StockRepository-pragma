package com.kevin.emazon.infraestructure.controllers.handler;

import com.kevin.emazon.application.dto.ExceptionResponseDto;
import com.kevin.emazon.infraestructure.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;


@RestControllerAdvice
public class HandlerExceptionController {

    public static final String ARGUMENT_NOT_VALID_MESSAGE = "Error en la creación del campo: ";



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ExceptionResponseDto>> inCaseThrowingConstraintViolationException(MethodArgumentNotValidException e){
        return ResponseEntity.status( HttpStatus.BAD_REQUEST).body(e.getFieldErrors().
                stream().
                map(ex -> new ExceptionResponseDto(ARGUMENT_NOT_VALID_MESSAGE +ex.getField() ,ex.getDefaultMessage(), HttpStatus.BAD_REQUEST)).toList());
    }

    @ExceptionHandler(InvalidOrderingMethodException.class)
    public ResponseEntity<ExceptionResponseDto> inCaseThrowingInvalidOrderingMethod(RuntimeException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponseDto(e.getClass().getCanonicalName(), e.getMessage(), HttpStatus.BAD_REQUEST));
    }

}
