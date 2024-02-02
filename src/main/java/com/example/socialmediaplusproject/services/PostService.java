package com.example.socialmediaplusproject.services;

import com.example.socialmediaplusproject.dto.PostRequestDto;
import com.example.socialmediaplusproject.dto.PostRetrieveDto;
import com.example.socialmediaplusproject.dto.UserSimpleDto;
import com.example.socialmediaplusproject.dto.VoteDto;
import com.example.socialmediaplusproject.models.Post;
import com.example.socialmediaplusproject.models.Uzer;
import com.example.socialmediaplusproject.models.Vote;
import com.example.socialmediaplusproject.repositories.PostRepo;
import com.example.socialmediaplusproject.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepo postRepo;
    private final UserRepo userRepo;

    public void post(PostRequestDto dto) {
        Uzer author = userRepo.findByUsername(dto.author());
        Post post = new Post(dto.title(), dto.content());
        author.post(post);
        post.setAuthor(author);
        this.postRepo.save(post);
        this.userRepo.save(author);
    }

    public List<PostRetrieveDto> getAll() {
        return postRepo.findAll().stream()
                .map(this::fillPostDto)
                .collect(Collectors.toList());
    }

    public List<PostRetrieveDto> getByAuthorId(long id) {
        return postRepo.findAllByAuthorId(id).stream()
                .map(this::fillPostDto)
                .collect(Collectors.toList());
    }

    private PostRetrieveDto fillPostDto(Post post) {
        return new PostRetrieveDto(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                new UserSimpleDto(
                        post.getAuthor().getId(),
                        post.getAuthor().getUsername(),
                        post.getAuthor().getProfileImagePath()
                ),
                getVotes(post),
                getRating(post),
                post.formatCreationTime()
        );
    }

    private List<VoteDto> getVotes(Post post) {
        return post.getVotes().stream()
                .map(this::fillVoteDto)
                .collect(Collectors.toList());
    }

    private VoteDto fillVoteDto(Vote vote) {
        return new VoteDto(
                vote.getVoter().getUsername(),
                vote.getBias()
        );
    }

    private int getRating(Post post) {
        int rating = 0;
        for (Vote v : post.getVotes()) {
            rating += v.getBias();
        }
        return rating;
    }
}