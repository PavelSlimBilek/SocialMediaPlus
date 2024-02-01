package com.example.socialmediaplusproject.controllers;

import com.example.socialmediaplusproject.services.PostService;
import com.example.socialmediaplusproject.services.SecurityService;
import com.example.socialmediaplusproject.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class AppController {

    private final PostService postService;
    private final SecurityService securityService;
    private final UserService userService;

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

        model.addAttribute("posts", postService.getAll());
        return "home";
    }

    @GetMapping("/profile")
    public String getProfile(Model model) {

        model.addAttribute("userProfile", securityService.getLoggedInUser());
        return "profile";
    }

    @GetMapping("/manage")
    public String manageProfile(Model model) {

        model.addAttribute("userProfile", userService.get(securityService.getLoggedInUser().id()));
        return "manage";
    }

    @GetMapping("/profile/{id}")
    public String getProfile(Model model,
                             @PathVariable Long id) {

        model.addAttribute("userProfile", userService.get(id));
        return "profile";
    }

    @GetMapping("/people")
    public String getProfiles(Model model) {

        model.addAttribute("userProfiles", userService.getAll());
        return "people";
    }
}