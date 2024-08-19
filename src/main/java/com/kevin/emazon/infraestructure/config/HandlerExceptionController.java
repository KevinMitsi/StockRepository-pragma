package com.kevin.emazon.infraestructure.config;

import com.kevin.emazon.application.dto.ExceptionResponseDto;
import com.kevin.emazon.infraestructure.exceptions.BrandException;
import com.kevin.emazon.infraestructure.exceptions.CategoryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerExceptionController {

    @ExceptionHandler(CategoryException.class)
    public ResponseEntity<ExceptionResponseDto> inCaseThrowingCategoryException(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponseDto("Category Exception", e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(BrandException.class)
    public ResponseEntity<ExceptionResponseDto> inCaseThrowingBrandException(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponseDto("Brand Exception", e.getMessage(), HttpStatus.BAD_REQUEST));
    }
}
