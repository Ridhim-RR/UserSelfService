package com.example.userselfservice.Dtos;

import lombok.Data;

@Data
public class SignUpReqDto {
    private String name;
    private String email;
    private String password;
}
