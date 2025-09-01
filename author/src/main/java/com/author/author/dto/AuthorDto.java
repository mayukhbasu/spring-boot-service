package com.author.author.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {
    private Long id;
    private String name;
    private String bio;
    private Double royaltyPercentage;
    private String publisherId;
}
