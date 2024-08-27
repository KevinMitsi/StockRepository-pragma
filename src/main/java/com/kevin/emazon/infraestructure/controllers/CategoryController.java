package com.kevin.emazon.infraestructure.controllers;

import com.kevin.emazon.application.dto.CategoryDto;
import com.kevin.emazon.application.handler.ICategoryHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
@Validated
public class CategoryController {
    private final ICategoryHandler categoryHandler;
    @PostMapping("/new")
    public ResponseEntity<String> createCategory(@Valid @RequestBody CategoryDto categoryRequest) {
        categoryHandler.saveCategory(categoryRequest);
        return  ResponseEntity.status(HttpStatus.CREATED).body("Felicidades, ha creado satisfactoriamente su categoría " +categoryRequest.getName());
    }

    @GetMapping("/getall/{ordering}")
    public ResponseEntity<Page<CategoryDto>> getAllCategories(@PathVariable String ordering){
        return ResponseEntity.ok().body(categoryHandler.getAllCategories(ordering));
    }
}
