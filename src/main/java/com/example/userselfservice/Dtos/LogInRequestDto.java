package com.example.userselfservice.Dtos;

import lombok.Data;

@Data
public class LogInRequestDto {
    private String email;
    private String password;
}
