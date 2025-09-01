package com.practice.order.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.practice.order.events.BookSoldEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BookEventPublisher {
    

    private final KafkaTemplate<String, BookSoldEvent> kafkaTemplate;
    public void publishBookSold(BookSoldEvent event) {
        kafkaTemplate.send("book-sold-topic", String.valueOf(event.getBookId()) , event);
    }
}
