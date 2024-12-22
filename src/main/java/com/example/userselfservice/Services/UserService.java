package com.example.userselfservice.Services;

import com.example.userselfservice.Exceptions.InvalidInputException;
import com.example.userselfservice.Exceptions.UserAlreadyExistsException;
import com.example.userselfservice.Models.Token;
import com.example.userselfservice.Models.User;
import com.example.userselfservice.Repo.TokenRepo;
import com.example.userselfservice.Repo.UserRepo;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
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

    public User signup(String email,String name,String password) throws UserAlreadyExistsException {
        Optional<User> user = userRepo.findByEmail(email);
        if(user.isPresent()){
            throw new UserAlreadyExistsException("Email Already Exists", HttpStatus.BAD_REQUEST);
        }
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setName(name);
        newUser.setHashedPassword(encoder.encode(password));
        return  userRepo.save(newUser);
    }

    public Token login(String email,String password) throws RuntimeException, InvalidInputException{
        Optional<User> user = userRepo.findByEmail(email);
        if(user.isEmpty()){
            throw new InvalidInputException("User not found", HttpStatus.NOT_FOUND);
        }
        if(!encoder.matches(password, user.get().getHashedPassword())){
            throw new InvalidInputException("Wrong password", HttpStatus.BAD_REQUEST);
        }
        Token token = generateToken(user.get());
        if(token == null){
            throw new RuntimeException("Token generation failed");
        }
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
        List<Token> tokenList = tokenRepo.findByUserAndIsValidTrue(u);
        if(!tokenList.isEmpty() && tokenList.size() >= 2){
           return null;
        }
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

    public User validateToken(String token) throws RuntimeException {
    Optional<Token> optionalToken = tokenRepo.findByTokenAndIsValidAndExpiryGreaterThan(token,true,new Date());
    if(optionalToken.isEmpty()){
        return null;
    }
    return optionalToken.get().getUser();
    }
}
