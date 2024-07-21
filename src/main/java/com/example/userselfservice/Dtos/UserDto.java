package com.example.userselfservice.Dtos;

import com.example.userselfservice.Models.Roles;
import com.example.userselfservice.Models.User;
import lombok.Data;

import java.util.List;
@Data
public class UserDto {
    private String name;
    private String email;
    private Boolean isVerified;
    private List<Roles> role;


    public static UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRoles());
        return userDto;
    }
}
