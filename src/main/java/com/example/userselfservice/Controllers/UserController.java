package com.example.userselfservice.Controllers;

import com.example.userselfservice.Dtos.*;
import com.example.userselfservice.Models.Token;
import com.example.userselfservice.Models.User;
import com.example.userselfservice.Services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

  @PostMapping("/login")
    public LoginResDto login(@RequestBody loginReqDto req) throws Exception{
        LoginResDto resDto = new LoginResDto();
        Token token = userService.login(req.getEmail(), req.getPassword());
        if(token == null) {
            throw new RuntimeException("Something went wrong");
        }
        resDto.setToken(token.getToken());
        resDto.setIsValid(token.isValid());
        resDto.setExpiry(token.getExpiry());
      return resDto;
  }

  @PostMapping("/logout")
  public ResponseEntity<Void> logout(@RequestBody logoutRequestDto req){
    userService.logout(req.getToken());
    return new ResponseEntity<>(HttpStatus.OK);

  }
}
