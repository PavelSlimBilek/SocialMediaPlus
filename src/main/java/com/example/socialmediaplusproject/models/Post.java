package com.example.socialmediaplusproject.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String content;
    private LocalDateTime creationTime;

    @ManyToOne
    private Uzer author;
    @OneToMany
    private List<Vote> votes;

    public Post(String title, String content) {
        this.title = title;
        this.content = content;

        this.creationTime = LocalDateTime.now();
    }

    public String formatCreationTime() {
        return creationTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
    }

    public Integer getRating() {
        int totalBias = 0;
        if (votes != null) {
            for (Vote vote : votes) {
                totalBias += vote.getBias();
            }
        }
        return totalBias;
    }
}