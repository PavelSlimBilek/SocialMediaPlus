package com.example.socialmediaplusproject.controllers;

import com.example.socialmediaplusproject.repositories.PostRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AppController {

    private final PostRepo postRepo;

    @GetMapping("/register")
    public String registration() {

        return "registration";
    }

    @GetMapping("/login")
    public String login() {

        return "login";
    }

    @GetMapping("/home")
    public String goHome(Model model) {

        model.addAttribute("posts", this.postRepo.findAll());
        return "home";
    }
}