package com.practice.inventory.mapper;

import com.practice.inventory.dto.InventoryDTO;
import com.practice.inventory.entity.Inventory;

public class InventoryMapper {
    
    public static InventoryDTO toDTO(Inventory entity) {
        return InventoryDTO.builder()
                .id(entity.getId())
                .bookId(entity.getBookId())
                .region(entity.getRegion())
                .format(entity.getFormat())
                .availableCount(entity.getAvailableCount())
                .threshold(entity.getThreshold())
                .digitalAvailable(entity.getDigitalAvailable())
                .publisherId(entity.getPublisherId())
                .lastRestockDate(entity.getLastRestockDate())
                .updatedAt(entity.getUpdatedAt())
                .build();

    }

    public static Inventory toEntity(InventoryDTO dto) {
        return Inventory.builder()
                .id(dto.getId())
                .bookId(dto.getBookId())
                .region(dto.getRegion())
                .format(dto.getFormat())
                .availableCount(dto.getAvailableCount())
                .threshold(dto.getThreshold())
                .digitalAvailable(dto.getDigitalAvailable())
                .publisherId(dto.getPublisherId())
                .build();
    }
}
