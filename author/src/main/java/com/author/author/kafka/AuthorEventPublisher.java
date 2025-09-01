package com.author.author.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.author.author.events.AuthorCreatedEvent;
import com.author.author.events.AuthorUpdatedEvent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorEventPublisher {
    
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishCreated(AuthorCreatedEvent authorCreatedEvent) {
        kafkaTemplate.send("author.created", authorCreatedEvent.getId().toString(), authorCreatedEvent);
    }

    public void publishUpdated(AuthorUpdatedEvent event) {
        kafkaTemplate.send("author.updated", event.getId().toString(), event);
    }
}
