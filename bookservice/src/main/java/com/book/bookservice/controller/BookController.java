package com.book.bookservice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.book.bookservice.dto.BookDTO;
import com.book.bookservice.service.BookService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    // 📘 Create Book
    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO dto) {
        return ResponseEntity.ok(bookService.createBook(dto));
    }

    // ✏️ Update Book
    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO dto) {
        return ResponseEntity.ok(bookService.updateBook(id, dto));
    }

    // 🔍 Get Book by ID
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.findBookById(id));
    }

    // 📋 List Books (filter by authorId, category)
    @GetMapping
    public ResponseEntity<List<BookDTO>> getBooks(
            @RequestParam(required = false) Long authorId,
            @RequestParam(required = false) String category
    ) {
        return ResponseEntity.ok(bookService.findAll(authorId, category));
    }

    // ❌ Delete Book
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
