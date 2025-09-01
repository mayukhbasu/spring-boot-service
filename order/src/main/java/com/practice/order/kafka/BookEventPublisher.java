package com.practice.order.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.practice.events.BookSoldEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class BookEventPublisher {
    

    private final KafkaTemplate<String, BookSoldEvent> kafkaTemplate;
    public void publishBookSold(BookSoldEvent event) {
        log.info("Message published successfully");
        kafkaTemplate.send("book-sold-topic", String.valueOf(event.getBookId()) , event);
    }
}
