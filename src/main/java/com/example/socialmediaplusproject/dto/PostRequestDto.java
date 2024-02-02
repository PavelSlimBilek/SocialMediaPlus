package com.example.socialmediaplusproject.dto;

public record PostRequestDto(
        String author,
        String title,
        String content
) {
}
