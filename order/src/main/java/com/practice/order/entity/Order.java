package com.practice.order.entity;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long bookId;

    @Column(nullable = false)
    private String region;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private String format; // eBook or Paperback

    private String userId; // Optional: who placed it

    private String publisherId; // For multitenancy support

    @CreationTimestamp
    private Instant soldAt; // Order placed time
}
