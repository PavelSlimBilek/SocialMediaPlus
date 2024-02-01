package com.example.socialmediaplusproject.controllers;

import com.example.socialmediaplusproject.dto.RegistrationDto;
import com.example.socialmediaplusproject.dto.UpdateDto;
import com.example.socialmediaplusproject.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public String registerUser(@ModelAttribute RegistrationDto dto) {

        userService.registerUser(dto);
        return "redirect:/login";
    }

    @PostMapping("/update")
    public String updateInfo(@ModelAttribute UpdateDto dto) {

        userService.update(dto);
        return "redirect:/profile";
    }
}