package com.practice.order.events;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookSoldEvent {
    private Long bookId;
    private String region;
    private Integer quantity;
    private String format;
    private Instant soldAt;
    private String publisherId;
}