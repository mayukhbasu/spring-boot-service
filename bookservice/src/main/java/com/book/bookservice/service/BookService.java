package com.book.bookservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.book.bookservice.dto.BookDTO;
import com.book.bookservice.entity.Book;
import com.book.bookservice.events.BookCreatedEvent;
import com.book.bookservice.events.BookUpdatedEvent;
import com.book.bookservice.kafka.BookEventPublisher;
import com.book.bookservice.mapper.BookMapper;
import com.book.bookservice.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {
    
    private final BookRepository bookRepository;
    private final BookEventPublisher eventPublisher;

    public BookDTO createBook(BookDTO bookDTO) {
        Book saved = bookRepository.save(BookMapper.toEntity(bookDTO));
        eventPublisher.publishCreatedEevent(BookCreatedEvent.builder()
            .id(saved.getId())
                .title(saved.getTitle())
                .authors(saved.getAuthors())
                .category(saved.getCategory())
                .edition(saved.getEdition())
                .pricePerRegion(saved.getPricePerRegion())
                .royaltyPercentage(saved.getRoyalityPercentage())
                .publisherId(saved.getPublisherId())
                .approved(saved.isApproved())
                .build()); 
        return BookMapper.toDto(saved);
    }

    public BookDTO updateBook(Long id, BookDTO dto) {
        Optional<Book> optional = bookRepository.findById(id);
        if (optional.isEmpty()) throw new RuntimeException("Book not found");

        Book book = optional.get();
        book.setTitle(dto.getTitle());
        book.setAuthors(dto.getAuthors());
        book.setCategory(dto.getCategory());
        book.setEdition(dto.getEdition());
        book.setPricePerRegion(dto.getPricePerRegion());
        
        book.setPublisherId(dto.getPublisherId());
        book.setApproved(dto.isApproved());

        Book updated = bookRepository.save(book);

        eventPublisher.publishUpdatedEvent(BookUpdatedEvent.builder()
                .id(updated.getId())
                .title(updated.getTitle())
                .authors(updated.getAuthors())
                .category(updated.getCategory())
                .edition(updated.getEdition())
                .pricePerRegion(updated.getPricePerRegion())
                .royaltyPercentage(updated.getRoyalityPercentage())
                .publisherId(updated.getPublisherId())
                .approved(updated.isApproved())
                .build());

        return BookMapper.toDto(updated);
    }

    public BookDTO findBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        return BookMapper.toDto(book);
    }

    public List<BookDTO> findAll(Long authorId, String category) {
        List<Book> books;

        if (authorId != null && category != null) {
            books = bookRepository.findByAuthorsContainingAndCategory(authorId, category);
        } else if (authorId != null) {
            books = bookRepository.findByAuthorsContaining(authorId);
        } else if (category != null) {
            books = bookRepository.findByCategory(category);
        } else {
            books = bookRepository.findAll();
        }

        return books.stream().map(BookMapper::toDto).toList();
    }

    public void delete(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found");
        }
        bookRepository.deleteById(id);
    }

}
