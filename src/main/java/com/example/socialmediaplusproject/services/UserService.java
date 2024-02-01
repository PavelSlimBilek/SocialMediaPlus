package com.example.socialmediaplusproject.services;

import com.example.socialmediaplusproject.dto.RegistrationDto;
import com.example.socialmediaplusproject.dto.UpdateDto;
import com.example.socialmediaplusproject.dto.UserDto;
import com.example.socialmediaplusproject.models.Post;
import com.example.socialmediaplusproject.models.Uzer;
import com.example.socialmediaplusproject.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo repo;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(RegistrationDto dto) {
        Uzer user = new Uzer(
                dto.username(),
                passwordEncoder.encode(dto.password())
        );
        repo.save(user);
    }

    public void post(Post post) {
        Uzer user = this.repo.findByUsername(post.getAuthor().getUsername());
        user.addPost(post);
    }

    public Set<UserDto> getAll() {
        return repo.findAll().stream()
                .map(this::fillUserDto)
                .collect(Collectors.toSet());
    }

    public UserDto get(Long id) {
        return fillUserDto(repo.getReferenceById(id));
    }

    public void update(UpdateDto dto) {
        Uzer user = repo.getReferenceById(dto.id());
        user.setFirstName(dto.firstName().isBlank() ? user.getFirstName() : dto.firstName());
        user.setLastName(dto.lastName().isBlank() ? user.getLastName() : dto.lastName());
        user.setAbout(dto.about().isBlank() ? user.getAbout() : dto.about());
        user.setProfileImagePath(dto.profileImage().isBlank() ? user.getProfileImagePath() : dto.profileImage());
        repo.save(user);
    }

    private UserDto fillUserDto(Uzer u) {
        return new UserDto(
                u.getId(),
                u.getUsername(),
                u.getFirstName(),
                u.getLastName(),
                u.getAbout(),
                u.getProfileImagePath()
        );
    }
}