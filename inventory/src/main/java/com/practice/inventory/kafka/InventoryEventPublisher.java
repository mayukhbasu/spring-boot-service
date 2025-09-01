package com.practice.inventory.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.practice.inventory.event.StockBelowThresholdEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InventoryEventPublisher {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    private static final String TOPIC = "stock-below-threshold";

    public void publishStockBelowThreshold(StockBelowThresholdEvent event) {
        kafkaTemplate.send(TOPIC, event.getBookId(), event);
    }
}
