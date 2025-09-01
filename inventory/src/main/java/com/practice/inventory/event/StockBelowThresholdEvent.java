package com.practice.inventory.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockBelowThresholdEvent {
    private String bookId;
    private String region;
    private String format; // "Paperback", "eBook"
    private Integer availableCount;
    private Integer threshold;
    private String publisherId;
    private String message;
}
