package com.kevin.emazon.infraestructure.controllers;

import com.kevin.emazon.application.dto.ItemCartRequest;
import com.kevin.emazon.application.dto.ItemDto;
import com.kevin.emazon.application.dto.response.ItemResponseDto;
import com.kevin.emazon.application.handler.IItemHandler;
import com.kevin.emazon.application.dto.response.ItemCartResponse;
import com.kevin.emazon.domain.model.UpdateItemQuantityRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import static com.kevin.emazon.infraestructure.util.ConstantUtilInfraestructure.*;


@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("api/v1/item")
public class ItemController {
    private static final String CREATED_ITEM_MESSAGE = "Item creado correctamente ";

    private final IItemHandler itemHandler;


    @PostMapping("/new")
    @Secured(ROLE_ADMINISTRATOR)
    public ResponseEntity<String> createItem(@RequestBody ItemDto itemDto){
        itemHandler.saveItem(itemDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(CREATED_ITEM_MESSAGE + itemDto.getName());
    }

    @GetMapping("/getAll/byBrand/{name}")
    public ResponseEntity<Page<ItemResponseDto>> getAllByBrandName(@PathVariable String name,
                                                                   @RequestParam(defaultValue = DEFAULT_ORDERING) String order,
                                                                   @RequestParam(defaultValue = DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                                                   @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) Integer pageSize){
        return ResponseEntity.status(HttpStatus.OK).body(itemHandler.getAllByBrandName(name, order, pageNumber, pageSize));
    }
    @GetMapping("/getAll/byCategory/{name}")
    public ResponseEntity<Page<ItemResponseDto>> getAllByCategoryName(@PathVariable String name,
                                                                      @RequestParam(defaultValue = DEFAULT_ORDERING) String order,
                                                                      @RequestParam(defaultValue = DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                                                      @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) Integer pageSize){
        return ResponseEntity.status(HttpStatus.OK).body(itemHandler.getAllByCategoryName(name, order, pageNumber, pageSize));
    }
    @GetMapping("/getAll/byName/{name}")
    public ResponseEntity<Page<ItemResponseDto>> getAllByName(@PathVariable String name,
                                                              @RequestParam(defaultValue = DEFAULT_ORDERING) String order,
                                                              @RequestParam(defaultValue = DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) Integer pageSize){
        return ResponseEntity.status(HttpStatus.OK).body(itemHandler.getAllByName(name, order, pageNumber, pageSize));
    }


    //For external microservices
    @PostMapping("/itemCarts")
    @ResponseStatus(value = HttpStatus.OK)
    public List<ItemCartResponse> getItemsInUserCart(@Valid @RequestBody ItemCartRequest itemCartRequest){
        return itemHandler.geItemsInUserCart(itemCartRequest.getItemIds(), itemCartRequest.getCategoryToOrder(), itemCartRequest.getBrandToOrder());
    }

    @GetMapping("/exist/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public boolean existById(@PathVariable Long id){
        return itemHandler.existById(id);
    }
    @GetMapping("/isEnough/{id}/{quantity}")
    @ResponseStatus(code = HttpStatus.OK)
    public boolean isEnoughInStock(@PathVariable Long id, @PathVariable Long quantity){
        return itemHandler.isEnoughInStock(id, quantity);
    }

    @PatchMapping("/updateQuantity")
    @Secured({ROLE_AUX_BODEGA, ROLE_ADMINISTRATOR})
    public void updateQuantityOfItem(@RequestBody UpdateItemQuantityRequest updateRequest){
        itemHandler.updateStockItem(updateRequest.getItemId(), updateRequest.getAmountSupplied());
    }

    @PostMapping("/validateCategoryLimit")
    public boolean validateCategoryLimit(@RequestBody List<Long> itemIds) {
        return itemHandler.validateCategoryLimit(itemIds);
    }

    @GetMapping("/price/{itemId}")
    public Double getItemPriceByItemId(@PathVariable Long itemId){
        return itemHandler.getPriceByItemId(itemId);
    }

    @PatchMapping("reduceQuantity/{itemID}/{quantity}")
    @ResponseStatus(HttpStatus.OK)
    public void reduceQuantityInStock(@PathVariable Long itemID, @PathVariable Long quantity){
        itemHandler.reduceStock(itemID, quantity);
    }
}
