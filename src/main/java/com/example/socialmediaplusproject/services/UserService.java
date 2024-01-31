package com.example.socialmediaplusproject.services;

import com.example.socialmediaplusproject.dto.RegistrationDto;
import com.example.socialmediaplusproject.models.Post;
import com.example.socialmediaplusproject.models.Uzer;
import com.example.socialmediaplusproject.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(RegistrationDto dto) {
        Uzer user = new Uzer(
                dto.username(),
                passwordEncoder.encode(dto.password())
        );
        userRepo.save(user);
    }

    public void post(Post post) {
        Uzer user = this.userRepo.findByUsername(post.getAuthor().getUsername());
        user.addPost(post);
    }
}