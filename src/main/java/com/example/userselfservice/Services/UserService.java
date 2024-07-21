package com.example.userselfservice.Services;

import com.example.userselfservice.Models.User;
import com.example.userselfservice.Repo.UserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private BCryptPasswordEncoder encoder ;
    private UserRepo userRepo;
    UserService(BCryptPasswordEncoder encoder, UserRepo userRepo) {
        this.encoder = encoder;
        this.userRepo = userRepo;
    }
    public User signup(String email,String name,String password){
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setName(name);
        newUser.setHashedPassword(encoder.encode(password));

        return  userRepo.save(newUser);
    }
}
