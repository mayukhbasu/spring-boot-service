package com.book.bookservice.entity;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ElementCollection
    @CollectionTable(name="book_authors", joinColumns=@JoinColumn(name = "book_id"))
    @Column(name = "author_id")
    private List<Long> authors;
    @Column(nullable = false)
    private String category;
    private String edition;
    @ElementCollection
    @CollectionTable(name = "book_price_region", joinColumns = @JoinColumn(name = "book_id"))
    @MapKeyColumn(name = "region")
    @Column(name = "price")
    private Map<String, Double> pricePerRegion;
    @Transient
    private Double royalityPercentage;
    @Column(nullable = false)
    private String publisherId;
    @Column(nullable = false)
    private boolean approved;
    @CreationTimestamp
    @Column(updatable=false)
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updatedAt;
    
}
