package com.example.userselfservice.Services;

import com.example.userselfservice.Models.Token;
import com.example.userselfservice.Models.User;
import com.example.userselfservice.Repo.TokenRepo;
import com.example.userselfservice.Repo.UserRepo;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {
    private BCryptPasswordEncoder encoder ;
    private UserRepo userRepo;
    private TokenRepo tokenRepo;
    UserService(BCryptPasswordEncoder encoder, UserRepo userRepo, TokenRepo tokenRepo) {
        this.encoder = encoder;
        this.userRepo = userRepo;
        this.tokenRepo = tokenRepo;
    }
    public User signup(String email,String name,String password){
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setName(name);
        newUser.setHashedPassword(encoder.encode(password));

        return  userRepo.save(newUser);
    }

    public Token login(String email,String password) throws RuntimeException{
        Optional<User> user = userRepo.findByEmail(email);
        if(user.isEmpty()){
            throw new RuntimeException("User not found");
        }
        if(!encoder.matches(password, user.get().getHashedPassword())){
            throw new RuntimeException("Wrong password");
        }
        Token token = generateToken(user.get());
        Token newToken = tokenRepo.save(token);
        return newToken;
    }

    public void logout(String token) throws RuntimeException{
       Optional<Token> optionalToken = tokenRepo.findByTokenAndIsValid(token, true);
       if(optionalToken.isEmpty()){
          throw  new RuntimeException("Token not found");
       }
       Token t = optionalToken.get();
        t.setValid(false);
        tokenRepo.save(t);
    }
    private Token generateToken(User u){
        LocalDate currentDate = LocalDate.now();
        LocalDate expiryDate = currentDate.plusDays(30);
        Date expiry = Date.from(expiryDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Token token = new Token();
        token.setExpiry(expiry);
        token.setUser(u);
        token.setValid(true);
        token.setToken(RandomStringUtils.randomAlphanumeric(128));
        return token;
    }
}
