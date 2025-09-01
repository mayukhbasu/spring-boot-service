package com.practice.inventory.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.practice.inventory.dto.BookDTO;
import com.practice.inventory.dto.InventoryDTO;
import com.practice.inventory.entity.Inventory;
import com.practice.inventory.event.StockBelowThresholdEvent;
import com.practice.inventory.kafka.InventoryEventPublisher;
import com.practice.inventory.mapper.InventoryMapper;
import com.practice.inventory.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository repository;
    private final RestTemplate restTemplate;
    private final InventoryEventPublisher eventPublisher;

    private static final String BOOK_SERVICE_URL = "http://localhost:8082/books/";

    private void validateBook(String bookId) {
        try {
            BookDTO book = restTemplate.getForObject(BOOK_SERVICE_URL + bookId, BookDTO.class);
            if (book == null || book.getTitle() == null) {
                throw new RuntimeException("Invalid Book ID or missing title");
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public InventoryDTO addToInevntory(String bookId, InventoryDTO inventoryDTO) {
        validateBook(bookId);
        inventoryDTO.setBookId(bookId);
        Inventory saved = repository.save(InventoryMapper.toEntity(inventoryDTO));
        triggerStockBelowEventIfNeeded(saved);
        return InventoryMapper.toDTO(saved);
    }

    public InventoryDTO updateInventory(String bookId, InventoryDTO dto) {
        validateBook(bookId);
        Inventory inventory = repository
                .findByBookIdAndRegionAndFormat(bookId, dto.getRegion(), dto.getFormat())
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        inventory.setAvailableCount(dto.getAvailableCount());
        inventory.setThreshold(dto.getThreshold());
        inventory.setDigitalAvailable(dto.getDigitalAvailable());

        Inventory updated = repository.save(inventory);
        triggerStockBelowEventIfNeeded(updated);
        return InventoryMapper.toDTO(updated);
    }

    public List<InventoryDTO> getByBookId(String bookId) {
        return repository.findAllByBookId(bookId)
                .stream().map(InventoryMapper::toDTO).toList();
    }

    public List<InventoryDTO> filterInventory(String bookId, String region) {
        if (bookId != null && region != null) {
            return repository.findAllByBookIdAndRegion(bookId, region)
                    .stream().map(InventoryMapper::toDTO).collect(Collectors.toList());
        } else if (bookId != null) {
            return repository.findAllByBookId(bookId)
                    .stream().map(InventoryMapper::toDTO).collect(Collectors.toList());
        } else if (region != null) {
            return repository.findAllByRegion(region)
                    .stream().map(InventoryMapper::toDTO).collect(Collectors.toList());
        } else {
            return repository.findAll()
                    .stream().map(InventoryMapper::toDTO).collect(Collectors.toList());
        }
    }

    private void triggerStockBelowEventIfNeeded(Inventory inventory) {
        if (inventory.getAvailableCount() < inventory.getThreshold()) {
            StockBelowThresholdEvent event = StockBelowThresholdEvent.builder()
                    .bookId(inventory.getBookId())
                    .region(inventory.getRegion())
                    .format(inventory.getFormat())
                    .availableCount(inventory.getAvailableCount())
                    .threshold(inventory.getThreshold())
                    .publisherId(inventory.getPublisherId())
                    .message("⚠️ Stock is below threshold!")
                    .build();

            eventPublisher.publishStockBelowThreshold(event);
        }
    }
}
