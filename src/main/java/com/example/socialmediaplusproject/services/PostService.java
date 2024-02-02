package com.example.socialmediaplusproject.services;

import com.example.socialmediaplusproject.dto.PostDto;
import com.example.socialmediaplusproject.models.Post;
import com.example.socialmediaplusproject.models.Uzer;
import com.example.socialmediaplusproject.repositories.PostRepo;
import com.example.socialmediaplusproject.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepo postRepo;
    private final UserRepo userRepo;

    public void post(PostDto dto) {
        Uzer author = userRepo.findByUsername(dto.author());
        Post post = new Post(dto.title(), dto.content());
        author.addPost(post);
        post.setAuthor(author);
        this.postRepo.save(post);
        this.userRepo.save(author);
    }

    public List<Post> getAll() {
        return postRepo.findAll();
    }
    public List<Post> getByAuthorId(long id) {
        return postRepo.findAllByAuthorId(id);
    }
}