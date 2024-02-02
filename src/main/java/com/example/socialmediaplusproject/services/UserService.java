package com.example.socialmediaplusproject.services;

import com.example.socialmediaplusproject.dto.RegistrationDto;
import com.example.socialmediaplusproject.dto.UpdateDto;
import com.example.socialmediaplusproject.dto.UserModelDto;
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
        user.post(post);
    }

    public Set<UserModelDto> getAll() {
        return repo.findAll().stream()
                .map(this::fillUserDto)
                .collect(Collectors.toSet());
    }

    public UserModelDto get(Long id) {
        return fillUserDto(repo.getReferenceById(id));
    }

    public void update(UpdateDto dto) {
        Uzer user = repo.getReferenceById(dto.id());
        user.setFirstName(dto.firstName().trim().isBlank() ? user.getFirstName() : dto.firstName());
        user.setLastName(dto.lastName().trim().isBlank() ? user.getLastName() : dto.lastName());
        user.setAbout(dto.about().trim().isBlank() ? user.getAbout() : dto.about());
        user.setProfileImagePath(dto.profileImage().trim().isBlank() ? user.getProfileImagePath() : dto.profileImage());
        repo.save(user);
    }

    private UserModelDto fillUserDto(Uzer u) {
        return new UserModelDto(
                u.getId(),
                u.formatCreationTime(),
                u.getUsername(),
                u.getFirstName(),
                u.getLastName(),
                u.getAbout(),
                u.getProfileImagePath()
        );
    }
}