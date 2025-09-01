package com.book.bookservice.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.book.bookservice.events.BookCreatedEvent;
import com.book.bookservice.events.BookUpdatedEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class BookEventPublisher {
    
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String TOPIC_CREATED = "book.created.v1";
    private static final String TOPIC_UPDATED = "book.updated.v1";

    public void publishCreatedEevent(BookCreatedEvent event) {
        log.info("📘 Publishing BookCreatedEvent: {}", event);
        kafkaTemplate.send(TOPIC_CREATED, event.getId().toString(), event);
    }

    public void publishUpdatedEvent(BookUpdatedEvent bookUpdatedEevent) {
        log.info("📘 Publishing BookUpdatedEevent: {}", bookUpdatedEevent);
        kafkaTemplate.send(TOPIC_UPDATED, bookUpdatedEevent.getId().toString(), bookUpdatedEevent);
    }
 
}
