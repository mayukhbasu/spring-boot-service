package com.practice.inventory.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.practice.inventory.event.StockBelowThresholdEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class StockThresholdTopicListener {
    private static final String STOCK_THRESHOLD_TOPIC = "stock-below-threshold";

    @KafkaListener(topics=STOCK_THRESHOLD_TOPIC, groupId = "inventory-service")
    public void consume(StockBelowThresholdEvent event) {
        log.info("Threashold crosses {}",event.getThreshold());
    }
}
