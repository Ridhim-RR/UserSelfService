package com.example.userselfservice.Repo;

import com.example.userselfservice.Models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepo extends JpaRepository<Token, Long> {
    Optional<Token> findByTokenAndIsValid(String token, boolean isValid);
}
