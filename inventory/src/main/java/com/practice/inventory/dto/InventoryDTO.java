package com.practice.inventory.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDTO {
    private Long id;
    private String bookId;
    private String region;
    private String format;
    private Integer availableCount;
    private Integer threshold;
    private Boolean digitalAvailable;
    private String publisherId;
    private Instant lastRestockDate;
    private Instant updatedAt;
}
