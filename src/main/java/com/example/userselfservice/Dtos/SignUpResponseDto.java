package com.example.userselfservice.Dtos;

import com.example.userselfservice.Models.Roles;
import com.example.userselfservice.Models.User;
import lombok.Data;

import java.util.List;
@Data
public class SignUpResponseDto {
    private String name;
    private String email;
    private Boolean isVerified;
    private List<Roles> roles;
    private ResponseStatus status;


    public static SignUpResponseDto from(User u){
        SignUpResponseDto userDto = new SignUpResponseDto();
        userDto.setEmail(u.getEmail());
        userDto.setName(u.getName());
        userDto.setRoles(u.getRoles());
        return userDto;
    }



}
