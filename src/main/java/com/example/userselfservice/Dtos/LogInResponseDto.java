package com.example.userselfservice.Dtos;

import com.example.userselfservice.Models.Token;
import lombok.Data;

import java.util.Date;

@Data
public class LogInResponseDto {
    private String token;
    private Boolean isValid;
    private Date expiry;

    public static LogInResponseDto from(Token token) {
        LogInResponseDto res = new LogInResponseDto();
        res.setToken(token.getToken());
        res.setIsValid(token.isValid());
        res.setExpiry(token.getExpiry());
        return res;
    }
}
