package com.example.userselfservice.Dtos;

import com.example.userselfservice.Models.Token;
import lombok.Data;

import java.util.Date;

@Data
public class LoginResDto {
    private String token;
    private Boolean isValid;
    private Date expiry;

    public static LoginResDto from(Token token) {
        LoginResDto res = new LoginResDto();
        res.setToken(token.getToken());
        res.setIsValid(token.isValid());
        res.setExpiry(token.getExpiry());
        return res;
    }
}
