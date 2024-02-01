package com.example.socialmediaplusproject.dto;

public record UpdateDto(
        Long id,
        String firstName,
        String lastName,
        String about,
        String profileImage
) {
}
