package com.example.userselfservice.Controllers;

import com.example.userselfservice.Dtos.SignUpReqDto;
import com.example.userselfservice.Dtos.UserDto;
import com.example.userselfservice.Models.User;
import com.example.userselfservice.Services.UserService;
import jakarta.persistence.Entity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController  {
    private UserService userService;
    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
  public UserDto signup(@RequestBody SignUpReqDto req){
      UserDto user = new UserDto();
      User newUser = userService.signup(req.getEmail(), req.getName(), req.getPassword());
      return UserDto.toDto(newUser);
  }
}
