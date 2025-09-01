package com.practice.inventory.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.practice.events.BookSoldEvent;
import com.practice.inventory.service.InventoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class BookSoldEventListener {
    // private final KafkaTemplate<String, Object> kafkaTemplate;
    private final InventoryService inventoryService;
    @KafkaListener(topics = "book-sold-topic", groupId = "inventory-service")
    public void consume(BookSoldEvent event) {
        log.info("📥 Received BookSoldEvent: {}", event);

        try {
            inventoryService.deductStock(
                event.getBookId(),
                event.getRegion(),
                event.getFormat(),
                event.getQuantity()
            );
            log.info("✅ Stock updated successfully for bookId: {}", event.getBookId());
        } catch (Exception e) {
            log.error("❌ Failed to update stock for event: {}", event, e);
        }
    }
}
