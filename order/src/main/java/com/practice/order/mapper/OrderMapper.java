package com.practice.order.mapper;

import com.practice.order.dto.OrderDTO;
import com.practice.order.entity.Order;

public class OrderMapper {

    public static OrderDTO toDto(Order order) {
        return OrderDTO.builder()
                .id(order.getId())
                .bookId(order.getBookId())
                .region(order.getRegion())
                .quantity(order.getQuantity())
                .format(order.getFormat())
                .userId(order.getUserId())
                .publisherId(order.getPublisherId())
                .soldAt(order.getSoldAt())
                .build();
    }

    public static Order toEntity(OrderDTO dto) {
        return Order.builder()
                .bookId(dto.getBookId())
                .region(dto.getRegion())
                .quantity(dto.getQuantity())
                .format(dto.getFormat())
                .userId(dto.getUserId())
                .publisherId(dto.getPublisherId())
                .build(); // soldAt auto-filled
    }
}