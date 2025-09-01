package com.practice.order.dto;

import java.time.Instant;

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
public class OrderDTO {
    private Long id;
    private Long bookId;
    private String region;
    private Integer quantity;
    private String format;
    private String userId;
    private String publisherId;
    private Instant soldAt;
}
