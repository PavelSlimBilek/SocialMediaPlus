package com.example.socialmediaplusproject.controllers;

import com.example.socialmediaplusproject.dto.PostDto;
import com.example.socialmediaplusproject.services.PostService;
import com.example.socialmediaplusproject.services.SecurityService;
import com.example.socialmediaplusproject.services.VoteService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final VoteService voteService;
    private final SecurityService securityService;

    @GetMapping("/post")
    public String postForm() {

        return "create";
    }

    @PostMapping("/post")
    public String attemptToPost(@ModelAttribute PostDto dto) {

        if (securityService.getLoggedInUser().username().equals(dto.author())) {
            postService.post(dto);
        }
        return "redirect:/home";
    }

    @PostMapping("/voteUp")
    public String voteUp(@RequestParam("postID") Long postID,
                         @RequestParam("username") String username,
                         HttpServletRequest request) {

        voteService.voteUp(postID, username);
        String referer = request.getHeader("referer");
        return "redirect:" + (referer != null && !referer.isEmpty() ? referer : "/home");
    }

    @PostMapping("/voteDown")
    public String voteDown(@RequestParam("postID") Long postID,
                           @RequestParam("username") String username,
                           HttpServletRequest request) {

        voteService.voteDown(postID, username);
        String referer = request.getHeader("referer");
        return "redirect:" + (referer != null && !referer.isEmpty() ? referer : "/home");
    }
}