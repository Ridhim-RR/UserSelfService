package com.example.userselfservice.Repo;

import com.example.userselfservice.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User save(User user);
}
