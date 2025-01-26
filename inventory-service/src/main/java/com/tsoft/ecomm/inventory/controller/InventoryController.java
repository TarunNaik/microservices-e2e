package com.tsoft.ecomm.inventory.controller;

import com.tsoft.ecomm.inventory.dto.InventoryRequest;
import com.tsoft.ecomm.inventory.dto.InventoryResponse;
import com.tsoft.ecomm.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryResponse addInventory(@RequestBody InventoryRequest inventoryRequest) {
        return inventoryService.addInventory(inventoryRequest);
    }

    @GetMapping("/{skuCode}")
    @ResponseStatus(HttpStatus.OK)
    public Integer getInventory(@PathVariable String skuCode) {
        return inventoryService.getInventory(skuCode);
    }
}
