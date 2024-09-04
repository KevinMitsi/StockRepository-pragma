package com.kevin.emazon.infraestructure.controllers;

import com.kevin.emazon.application.dto.CategoryDto;
import com.kevin.emazon.application.handler.ICategoryHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
@Validated
public class CategoryController {
    private static final String CREATED_CATEGORY_MESSAGE = "Felicidades, ha creado satisfactoriamente su categoría ";
    private static final String ROLE_ADMINISTRATOR = "ROLE_ADMINISTRADOR";

    private final ICategoryHandler categoryHandler;

    @PostMapping("/new")
    @Secured(ROLE_ADMINISTRATOR)
    public ResponseEntity<String> createCategory(@Valid @RequestBody CategoryDto categoryRequest) {
        categoryHandler.saveCategory(categoryRequest);
        return  ResponseEntity.status(HttpStatus.CREATED).body(CREATED_CATEGORY_MESSAGE +categoryRequest.getName());
    }

    @GetMapping("/getall/{ordering}")
    public ResponseEntity<Page<CategoryDto>> getAllCategories(@PathVariable String ordering){
        return ResponseEntity.ok().body(categoryHandler.getAllCategories(ordering));
    }
}
