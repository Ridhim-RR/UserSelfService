package com.example.userselfservice.Repo;

import com.example.userselfservice.Models.Token;
import com.example.userselfservice.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TokenRepo extends JpaRepository<Token, Long> {
    Optional<Token> findByTokenAndIsValid(String token, boolean isValid);
    List<Token> findByUserAndIsValidTrue(User user);
    Optional<Token> findByTokenAndIsValidAndExpiryGreaterThan(String token, boolean isValid, Date currentDate);
}
