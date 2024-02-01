package com.example.socialmediaplusproject.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

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

    public Uzer(String userName, String password) {
        this.username = userName;
        this.password = password;
    }

    public void addPost(Post post) {
        this.posts.add(post);
    }

    public void addVote(Vote vote) {
        this.votes.add(vote);
    }

    public Uzer setImage(String url) {
        this.profileImagePath = url;
        return this;
    }
}