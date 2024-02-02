package com.example.socialmediaplusproject.services;

import com.example.socialmediaplusproject.dto.UserDto;
import com.example.socialmediaplusproject.models.Uzer;
import com.example.socialmediaplusproject.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final UserRepo repo;

    public UserDto getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Uzer user = repo.findByUsername(authentication.getName());
        return new UserDto(
                user.getId(),
                user.formatCreationTime(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getAbout(),
                user.getProfileImagePath()
        );
    }
}