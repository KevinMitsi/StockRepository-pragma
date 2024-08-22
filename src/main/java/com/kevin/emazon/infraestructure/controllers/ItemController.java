package com.kevin.emazon.infraestructure.controllers;

import com.kevin.emazon.application.dto.ItemDto;
import com.kevin.emazon.application.handler.IItemHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/item")
public class ItemController {
    private final IItemHandler itemHandler;
    @PostMapping("/new")
    public ResponseEntity<String> createItem(@RequestBody ItemDto itemDto){
        itemHandler.saveItem(itemDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Item creado correctamente "+ itemDto.getName());
    }
}
