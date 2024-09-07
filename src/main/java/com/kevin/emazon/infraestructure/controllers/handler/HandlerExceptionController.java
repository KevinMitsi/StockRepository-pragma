package com.kevin.emazon.infraestructure.controllers.handler;

import com.kevin.emazon.application.dto.ExceptionResponseDto;
import com.kevin.emazon.infraestructure.exceptions.BrandException;
import com.kevin.emazon.infraestructure.exceptions.CategoryException;
import com.kevin.emazon.infraestructure.exceptions.IncreaseItemStockException;
import com.kevin.emazon.infraestructure.exceptions.ItemException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;


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

    @ExceptionHandler(ItemException.class)
    public ResponseEntity<ExceptionResponseDto> inCaseThrowingItemException(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponseDto("Item Exception", e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponseDto> inCaseThrowingIllegalArgument(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponseDto("IllegalArgument Exception", e.getMessage(), HttpStatus.BAD_REQUEST));
    }
    @ExceptionHandler(IncreaseItemStockException.class)
    public ResponseEntity<ExceptionResponseDto> inCaseThrowingIncreaseItemStock(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponseDto("IncreaseItemStock Exception", e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ExceptionResponseDto>> inCaseThrowingConstraintViolationException(MethodArgumentNotValidException e){
        return ResponseEntity.status( HttpStatus.BAD_REQUEST).body(e.getFieldErrors().
                stream().
                map(ex -> new ExceptionResponseDto("Error en la creación del campo: "+ex.getField() ,ex.getDefaultMessage(), HttpStatus.BAD_REQUEST)).toList());
    }

}
