package com.example.socialmediaplusproject.repositories;

import com.example.socialmediaplusproject.models.Uzer;
import com.example.socialmediaplusproject.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepo extends JpaRepository<Vote, Long> {

    boolean existsByVoterAndPostId(@Param("voter") Uzer user, @Param("postId") Long postId);
    Optional<Vote> findFirstByVoterAndPostId(@Param("voter") Uzer user, @Param("postId") Long postId);
}