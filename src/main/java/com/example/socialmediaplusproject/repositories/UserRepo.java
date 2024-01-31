package com.example.socialmediaplusproject.repositories;

import com.example.socialmediaplusproject.models.Uzer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Uzer, Long> {
    Uzer findByUsername(String username);
}