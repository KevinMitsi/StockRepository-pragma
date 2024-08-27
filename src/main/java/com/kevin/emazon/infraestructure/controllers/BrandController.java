package com.kevin.emazon.infraestructure.controllers;

import com.kevin.emazon.application.dto.BrandDto;
import com.kevin.emazon.application.handler.IBrandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/brand")
@RequiredArgsConstructor
public class BrandController {
    private final IBrandHandler brandHandler;

    @PostMapping("/new")
    public ResponseEntity<String> createBrand(@RequestBody BrandDto brandDto){
        brandHandler.saveBrand(brandDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Felicidades ha creado la marca: "+brandDto.getName());
    }

    @GetMapping("/getall/{order}")
    public ResponseEntity<Page<BrandDto>> getAllBrands(@PathVariable String order){
        return ResponseEntity.status(HttpStatus.OK).body(brandHandler.getAll(order));
    }
}
