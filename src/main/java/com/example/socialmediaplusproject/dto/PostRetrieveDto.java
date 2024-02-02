package com.example.socialmediaplusproject.dto;

import java.util.List;

public record PostRetrieveDto(
        long id,
        String title,
        String content,
        UserSimpleDto author,
        List<VoteDto> votes,
        int rating,
        String creationTime
) {
}
