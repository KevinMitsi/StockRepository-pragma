package com.kevin.emazon.infraestructure.rest.advice;

import com.kevin.emazon.application.dto.ExceptionResponseDto;
import com.kevin.emazon.infraestructure.exceptions.CategoryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerExceptionController {

    @ExceptionHandler(CategoryException.class)
    public ResponseEntity<ExceptionResponseDto> wrongName(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponseDto("Category Exception", e.getMessage(), HttpStatus.BAD_REQUEST));
    }
}
