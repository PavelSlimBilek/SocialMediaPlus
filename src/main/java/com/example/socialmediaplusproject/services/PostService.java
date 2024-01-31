package com.example.socialmediaplusproject.services;

import com.example.socialmediaplusproject.models.Post;
import com.example.socialmediaplusproject.models.Uzer;
import com.example.socialmediaplusproject.repositories.PostRepo;
import com.example.socialmediaplusproject.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepo postRepo;
    private final UserRepo userRepo;

    public void post(String authorName, String title, String content) {
        Uzer author = userRepo.findByUsername(authorName);
        Post post = new Post(title, content);
        author.addPost(post);
        post.setAuthor(author);
        this.postRepo.save(post);
        this.userRepo.save(author);
    }

    public Post getPost(Long id) {
        return this.postRepo.getReferenceById(id);
    }

    public void savePost(Post post) {
        this.postRepo.save(post);
    }
}