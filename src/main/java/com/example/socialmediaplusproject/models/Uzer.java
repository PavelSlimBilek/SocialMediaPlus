package com.example.socialmediaplusproject.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Uzer {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime creationTime;

    private String username;
    private String password;

    private String profileImagePath = "/images/users/default.jpg";
    private String firstName = "";
    private String lastName = "";
    private String about = "No profile description";

    @OneToMany(mappedBy = "author")
    private final Set<Post> posts = new HashSet<>();
    @OneToMany(mappedBy = "voter")
    private final Set<Vote> votes = new HashSet<>();

    public Uzer(String username, String password) {
        this.username = username;
        this.password = password;

        this.creationTime = LocalDateTime.now();
    }

    public void post(Post post) {
        this.posts.add(post);
    }

    public void vote(Vote vote) {
        this.votes.add(vote);
    }

    public Uzer setImage(String url) {
        this.profileImagePath = url;
        return this;
    }

    public String formatCreationTime() {
        return this.creationTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }
}