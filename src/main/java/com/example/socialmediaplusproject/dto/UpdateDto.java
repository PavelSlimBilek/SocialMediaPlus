package com.example.socialmediaplusproject.dto;

public record UpdateDto(
        long id,
        String firstName,
        String lastName,
        String about,
        String profileImage
) {
}
