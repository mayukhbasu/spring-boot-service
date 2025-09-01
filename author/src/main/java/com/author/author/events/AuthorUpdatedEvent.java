package com.author.author.events;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorUpdatedEvent {
    private Long id;
    private String name;
    private String publisherId;
}
