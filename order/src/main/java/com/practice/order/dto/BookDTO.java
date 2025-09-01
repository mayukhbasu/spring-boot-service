package com.practice.order.dto;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private Long id;
    private String title;
    private List<Long> authors;  // List of author IDs
    private String category;
    private String edition;
    private Map<String, Double> pricePerRegion;
    private Double royaltyPercentage; // Computed at runtime
    private String publisherId;
    private boolean approved;
    private Instant createdAt;
    private Instant updatedAt;
}
