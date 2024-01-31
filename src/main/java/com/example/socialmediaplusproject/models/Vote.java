package com.example.socialmediaplusproject.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Vote {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Uzer voter;
    @ManyToOne
    private Post post;

    private int bias;

    public Vote(Post post, Uzer user, int bias) {
        this.post = post;
        this.voter = user;
        this.bias = bias;
    }
}