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

import static com.kevin.emazon.infraestructure.util.ConstantUtilInfraestructure.*;

@RestController
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
@Validated
public class CategoryController {
    private static final String CREATED_CATEGORY_MESSAGE = "Felicidades, ha creado satisfactoriamente su categoría ";

    private final ICategoryHandler categoryHandler;

    @PostMapping("/new")
    @Secured(ROLE_ADMINISTRATOR)
    public ResponseEntity<String> createCategory(@Valid @RequestBody CategoryDto categoryRequest) {
        categoryHandler.saveCategory(categoryRequest);
        return  ResponseEntity.status(HttpStatus.CREATED).body(CREATED_CATEGORY_MESSAGE +categoryRequest.getName());
    }

    @GetMapping("/getAll")
    public ResponseEntity<Page<CategoryDto>> getAllCategories(@RequestParam(defaultValue = DEFAULT_ORDERING) String order,
                                                              @RequestParam(defaultValue = DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) Integer pageSize){
        return ResponseEntity.ok().body(categoryHandler.getAllCategories(order, pageNumber, pageSize));
    }
}
