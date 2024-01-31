package com.example.socialmediaplusproject.controllers;

import com.example.socialmediaplusproject.services.PostService;
import com.example.socialmediaplusproject.services.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final VoteService voteService;

    @GetMapping("/create")
    public String postForm() {

        return "create";
    }

    @PostMapping("/create")
    public String attemptToPost(@RequestParam("author") String author,
                                @RequestParam("title") String title,
                                @RequestParam("content") String content) {

        postService.post(author, title, content);
        return "redirect:/home";
    }

    @PostMapping("/voteUp")
    public String voteUp(@RequestParam("postID") Long postID,
                       @RequestParam("username") String username) {

        voteService.voteUp(postID, username);
        return "redirect:/home";
    }

    @PostMapping("/voteDown")
    public String voteDown(@RequestParam("postID") Long postID,
                       @RequestParam("username") String username) {

        voteService.voteDown(postID, username);
        return "redirect:/home";
    }
}