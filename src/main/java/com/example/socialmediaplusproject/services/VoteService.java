package com.example.socialmediaplusproject.services;

import com.example.socialmediaplusproject.models.Post;
import com.example.socialmediaplusproject.models.Uzer;
import com.example.socialmediaplusproject.models.Vote;
import com.example.socialmediaplusproject.repositories.PostRepo;
import com.example.socialmediaplusproject.repositories.UserRepo;
import com.example.socialmediaplusproject.repositories.VoteRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepo voteRepo;
    private final PostRepo postRepo;
    private final UserRepo userRepo;

    public void voteUp(Long postID, String username) {
        Uzer user = userRepo.findByUsername(username);
        if (user == null) {
            return;
        }

        if (checkUserVote(user, postID)) {
            Vote vote = voteRepo.findFirstByVoterAndPostId(user, postID).orElse(null);
            if (vote != null && vote.getBias() <= 0) {
                vote.setBias(vote.getBias() + 1);
                voteRepo.save(vote);
            }
        } else {
            Post post = postRepo.findById(postID).orElse(null);
            Vote vote = new Vote(post, user, 1);
            updateData(user, post, vote);
        }
    }

    public void voteDown(Long postID, String username) {
        Uzer user = userRepo.findByUsername(username);
        if (user == null) {
            return;
        }
        if (checkUserVote(user, postID)) {
            Vote vote = voteRepo.findFirstByVoterAndPostId(user, postID).orElse(null);
            if (vote != null && vote.getBias() >= 0) {
                vote.setBias(vote.getBias() - 1);
                voteRepo.save(vote);
            }
        } else {
            Post post = postRepo.findById(postID).orElse(null);
            Vote vote = new Vote(post, user, -1);
            updateData(user, post, vote);
        }
    }

    public boolean checkUserVote(Uzer user, Long postID) {
        return this.voteRepo.existsByVoterAndPostId(user, postID);
    }

    private void updateData(Uzer user, Post post, Vote vote) {
        if (post == null) {
            return;
        }
        vote.setPost(post);
        post.getVotes().add(vote);

        vote.setVoter(user);
        user.addVote(vote);
        voteRepo.save(vote);
        userRepo.save(user);
        postRepo.save(post);
    }
}