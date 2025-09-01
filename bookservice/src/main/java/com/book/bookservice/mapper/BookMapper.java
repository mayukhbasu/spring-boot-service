package com.book.bookservice.mapper;

import com.book.bookservice.dto.BookDTO;
import com.book.bookservice.entity.Book;

public class BookMapper {
    
    public static BookDTO toDto(Book book) {
        return BookDTO.builder().id(book.getId())
        .authors(book.getAuthors())
        .approved(book.isApproved())
        .category(book.getCategory())
        .edition(book.getEdition())
        .pricePerRegion(book.getPricePerRegion())
                .royaltyPercentage(book.getRoyalityPercentage()) // runtime-only
                .publisherId(book.getPublisherId())
                .approved(book.isApproved())
                .title(book.getTitle())
                .createdAt(book.getCreatedAt())
                .updatedAt(book.getUpdatedAt())
                .build();
    }

    public static Book toEntity(BookDTO dto) {
         return Book.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .authors(dto.getAuthors())
                .category(dto.getCategory())
                .edition(dto.getEdition())
                .pricePerRegion(dto.getPricePerRegion())
                .publisherId(dto.getPublisherId())
                .approved(dto.isApproved())
                .build();
    }
}
