package com.practice.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.order.entity.Order;

public interface OrderRepository  extends JpaRepository<Order, Long>{
    List<Order> findByBookId(Long bookId);
    List<Order> findByRegion(String region);
    List<Order> findByBookIdAndRegion(Long bookId, String region);
}
