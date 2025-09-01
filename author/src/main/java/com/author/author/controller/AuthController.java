package com.author.author.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.author.author.dto.AuthorDto;
import com.author.author.service.AuthService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authors")
@Slf4j
public class AuthController {
    
    private final AuthService authService;

    @GetMapping
    public ResponseEntity<List<AuthorDto>> getAll() {
        System.out.println();
        return ResponseEntity.ok(authService.getAllAuthors());
    }
    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable String id) {
        return ResponseEntity.ok(authService.findAuthorByID(id));
    }

    @PostMapping
    public ResponseEntity<AuthorDto> create(@RequestBody AuthorDto dto) {
        System.out.println("Inside create method");
        return ResponseEntity.ok(authService.createAuthor(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable Long id, @RequestBody AuthorDto authorDto) {
        return ResponseEntity.ok(authService.updateAuthor(id, authorDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = authService.deleteAuthorById(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
