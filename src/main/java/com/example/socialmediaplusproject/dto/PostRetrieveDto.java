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

    public boolean hasUserVotedUp(String username) {
        for (VoteDto v : votes) {
            if (v.voterUsername().equals(username) && v.bias() > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean hasUserVotedDown(String username) {
        for (VoteDto v : votes) {
            if (v.voterUsername().equals(username) && v.bias() < 0) {
                return true;
            }
        }
        return false;
    }
}
