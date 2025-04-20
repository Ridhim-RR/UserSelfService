package com.example.userselfservice.Dtos;

import com.example.userselfservice.Models.Roles;
import lombok.Data;

import java.util.List;

@Data
public class SignUpRequestDto {
    private String name;
    private String email;
    private String password;
    private List<Roles> roles;
}
