package com.kevin.emazon.infraestructure.controllers.controllers;

import com.kevin.emazon.application.dto.CategoryDto;
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
    public ResponseEntity<String> createCategory(@RequestBody CategoryDto categoryRequest) {
        categoryHandler.saveCategory(categoryRequest);
        return  ResponseEntity.status(HttpStatus.CREATED).body("Felicidades, ha creado satisfactoriamente su categoría " +categoryRequest.getName());
    }
}
