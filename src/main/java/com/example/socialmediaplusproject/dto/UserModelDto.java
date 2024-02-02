package com.example.socialmediaplusproject.dto;

public record UserModelDto(
        long id,
        String creationTime,
        String username,
        String firstName,
        String lastName,
        String about,
        String profileImage
) {
}
