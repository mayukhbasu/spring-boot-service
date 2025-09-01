package com.author.author.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.author.author.dto.AuthorDto;
import com.author.author.entity.Author;
import com.author.author.events.AuthorCreatedEvent;
import com.author.author.events.AuthorUpdatedEvent;
import com.author.author.kafka.AuthorEventPublisher;
import com.author.author.repository.AuthorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final AuthorRepository authorRepository;
    private final AuthorEventPublisher authorEventPublisher;

    private AuthorDto mapToDto(Author author) {
        return AuthorDto.builder().id(author.getId()).name(author.getName())
        .bio(author.getBio())
        .publisherId(author.getPublisherId()).royaltyPercentage(author.getRoyaltyPercentage()).build();
    }

    private Author mapToEntity(AuthorDto authorDto) {
        return Author.builder().id(authorDto.getId())
        .bio(authorDto.getBio())
        .name(authorDto.getName())
        .royaltyPercentage(authorDto.getRoyaltyPercentage())
        .publisherId(authorDto.getPublisherId())
        .build();
    }

    public List<AuthorDto> getAllAuthors() {
        return authorRepository.findAll().stream().map(this::mapToDto).toList();
    }

    public AuthorDto findAuthorByID(String id) {
        return authorRepository.findById(Long.valueOf(id)).map(this::mapToDto).orElseThrow(() -> new RuntimeException("Author Not found"));
    }

    public AuthorDto createAuthor(AuthorDto authorDto) {
        Author author = mapToEntity(authorDto);
        author.setId(null);
        Author saved = authorRepository.save(author);
        authorEventPublisher.publishCreated(new AuthorCreatedEvent(saved.getId(), saved.getName(), saved.getPublisherId()));
        return mapToDto(saved);
    }

    public AuthorDto updateAuthor(Long id, AuthorDto authorDto) {
        AuthorDto author = authorRepository.findById(id).map(existing -> {
            existing.setName(authorDto.getName());
                    existing.setBio(authorDto.getBio());
                    existing.setRoyaltyPercentage(authorDto.getRoyaltyPercentage());
                    existing.setPublisherId(authorDto.getPublisherId());
                    Author saved = authorRepository.save(existing);
                    authorEventPublisher.publishUpdated(new AuthorUpdatedEvent(saved.getId(), saved.getName(), saved.getPublisherId()));
                    return mapToDto(saved);
        }).orElseThrow(() -> new RuntimeException("Author not found"));
        return author;
    }

    public boolean deleteAuthorById(Long id) {
        if(authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
            return true;
        } 
        return false;
    }
    
}
