package com.kevin.emazon.infraestructure.rest.controllers;

import com.kevin.emazon.application.dto.request.CategoryRequestDto;
import com.kevin.emazon.application.handler.ICategoryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final ICategoryHandler categoryHandler;
    @PostMapping("/new")
    private ResponseEntity<Void> createCategory(@RequestBody CategoryRequestDto categoryRequest) {
        categoryHandler.saveCategory(categoryRequest);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
