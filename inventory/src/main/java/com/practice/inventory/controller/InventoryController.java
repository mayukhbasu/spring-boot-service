package com.practice.inventory.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.inventory.dto.InventoryDTO;
import com.practice.inventory.service.InventoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    // ➕ Add Stock (POST /inventory/{bookId})
    @PostMapping("/{bookId}")
    public ResponseEntity<InventoryDTO> addStock(
            @PathVariable Long bookId,
            @RequestBody InventoryDTO dto
    ) {
        return ResponseEntity.ok(inventoryService.addToInevntory(bookId, dto));
    }

    // 🔄 Update Stock (PUT /inventory/{bookId})
    @PutMapping("/{bookId}")
    public ResponseEntity<InventoryDTO> updateStock(
            @PathVariable Long bookId,
            @RequestBody InventoryDTO dto
    ) {
        return ResponseEntity.ok(inventoryService.updateInventory(bookId, dto));
    }

    // 🔍 Get Stock (GET /inventory/{bookId})
    @GetMapping("/{bookId}")
    public ResponseEntity<List<InventoryDTO>> getInventoryByBook(@PathVariable Long bookId) {
        return ResponseEntity.ok(inventoryService.getByBookId(bookId));
    }

    // 📋 List Stocks (GET /inventory?region=US)
    @GetMapping
    public ResponseEntity<List<InventoryDTO>> filterInventory(
            @RequestParam(required = false) Long bookId,
            @RequestParam(required = false) String region
    ) {
        return ResponseEntity.ok(inventoryService.filterInventory(bookId, region));
    }
}
