package com.practice.inventory.kafka;

import org.springframework.kafka.annotation.KafkaListener;

public class StockThresholdTopicListener {
    private static final String STOCK_THRESHOLD_TOPIC = "stock-below-threshold";

    @KafkaListener(topics=STOCK_THRESHOLD_TOPIC, groupId = "inventory-service")
    public void consume() {
        
    }
}
