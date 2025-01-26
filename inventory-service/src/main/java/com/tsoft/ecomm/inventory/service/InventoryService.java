package com.tsoft.ecomm.inventory.service;

import com.tsoft.ecomm.inventory.dto.InventoryRequest;
import com.tsoft.ecomm.inventory.dto.InventoryResponse;
import com.tsoft.ecomm.inventory.model.Inventory;
import com.tsoft.ecomm.inventory.repo.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryResponse addInventory(InventoryRequest inventoryRequest) {
        Inventory inventory = Inventory.builder()
                .skuCode(inventoryRequest.skuCode())
                .quantity(inventoryRequest.quantity())
                .build();
        inventoryRepository.save(inventory);
        log.info("Inventory added: {}", inventory);
        return new InventoryResponse(inventory.getId(), inventory.getSkuCode(), inventory.getQuantity());
    }

    public Integer getInventory(String skuCode) {
        Inventory inventory = inventoryRepository.findBySkuCode(skuCode)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));
        return inventory.getQuantity();
    }
}
