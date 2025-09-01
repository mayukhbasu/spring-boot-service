package com.practice.events;
import java.io.Serializable;
import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookSoldEvent implements Serializable {
    private Long bookId;
    private String region;
    private Integer quantity;
    private String format;
    private Instant soldAt;
    private String publisherId;
}
