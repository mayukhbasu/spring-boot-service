package com.practice.inventory.entity;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
@Table(name = "inventory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long bookId; // Can be UUID or Long stringified

    @Column(nullable = false)
    private String region; // e.g., US, IN

    @Column(nullable = false)
    private String format; // eBook, Paperback

    @Column(nullable = false)
    private Integer availableCount;

    @Column(nullable = false)
    private Integer threshold;

    @Column(nullable = false)
    private Boolean digitalAvailable;

    @Column(nullable = false)
    private String publisherId;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Instant lastRestockDate;

    @UpdateTimestamp
    private Instant updatedAt;
}