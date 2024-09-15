package com.kevin.emazon.infraestructure.controllers;

import com.kevin.emazon.application.dto.BrandDto;
import com.kevin.emazon.application.handler.IBrandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;


import static com.kevin.emazon.infraestructure.util.ConstantUtilInfraestructure.*;

@RestController
@RequestMapping("api/v1/brand")
@RequiredArgsConstructor
public class BrandController {

    private static final String CREATED_BRAND_MESSAGE = "Felicidades ha creado la marca: ";

    private final IBrandHandler brandHandler;

    @PostMapping("/new")
    @Secured(ROLE_ADMINISTRATOR)
    public ResponseEntity<String> createBrand(@RequestBody BrandDto brandDto){
        brandHandler.saveBrand(brandDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(CREATED_BRAND_MESSAGE +brandDto.getName());
    }

    @GetMapping("/getAll")
    public ResponseEntity<Page<BrandDto>> getAllBrands(@RequestParam(defaultValue = DEFAULT_ORDERING) String order,
                                                       @RequestParam(defaultValue = DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                                       @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) Integer pageSize){
        return ResponseEntity.status(HttpStatus.OK).body(brandHandler.getAll(order, pageNumber, pageSize));
    }
}
