package com.book.bookservice.events;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookUpdatedEvent
 {
    private Long id;
    private String title;
    private List<Long> authors;
    private String category;
    private String edition;
    private Map<String, Double> pricePerRegion;
    private Double royaltyPercentage;
    private String publisherId;
    private boolean approved;
}
