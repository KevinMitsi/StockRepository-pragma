package com.kevin.emazon.infraestructure.controllers.handler;

import com.kevin.emazon.application.dto.ExceptionResponseDto;
import com.kevin.emazon.infraestructure.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;


@RestControllerAdvice
public class HandlerExceptionController {

    public static final String ARGUMENT_NOT_VALID_MESSAGE = "Error en la creación del campo: ";

    @ExceptionHandler(CategoryException.class)
    public ResponseEntity<ExceptionResponseDto> inCaseThrowingCategoryException(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponseDto(e.getClass().getCanonicalName(), e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(BrandException.class)
    public ResponseEntity<ExceptionResponseDto> inCaseThrowingBrandException(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponseDto(e.getClass().getCanonicalName(), e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(ItemException.class)
    public ResponseEntity<ExceptionResponseDto> inCaseThrowingItemException(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponseDto(e.getClass().getCanonicalName(), e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponseDto> inCaseThrowingIllegalArgument(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponseDto(e.getClass().getCanonicalName(), e.getMessage(), HttpStatus.BAD_REQUEST));
    }
    @ExceptionHandler(IncreaseItemStockException.class)
    public ResponseEntity<ExceptionResponseDto> inCaseThrowingIncreaseItemStock(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponseDto(e.getClass().getCanonicalName(), e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ExceptionResponseDto>> inCaseThrowingConstraintViolationException(MethodArgumentNotValidException e){
        return ResponseEntity.status( HttpStatus.BAD_REQUEST).body(e.getFieldErrors().
                stream().
                map(ex -> new ExceptionResponseDto(ARGUMENT_NOT_VALID_MESSAGE +ex.getField() ,ex.getDefaultMessage(), HttpStatus.BAD_REQUEST)).toList());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ExceptionResponseDto> inCaseThrowingNoSuchElementException(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponseDto(e.getClass().getCanonicalName(), e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(InvalidOrderingMethodException.class)
    public ResponseEntity<ExceptionResponseDto> inCaseThrowingInvalidOrderingMethod(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponseDto(e.getClass().getCanonicalName(), e.getMessage(), HttpStatus.BAD_REQUEST));
    }

}
