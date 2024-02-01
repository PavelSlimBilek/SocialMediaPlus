package com.example.socialmediaplusproject.dto;

public record UserDto(
        Long id,
        String username,
        String firstName,
        String lastName,
        String about,
        String profileImage
) {
}
