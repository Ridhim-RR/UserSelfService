package com.example.userselfservice.Controllers;

import com.example.userselfservice.Dtos.*;
import com.example.userselfservice.Dtos.ResponseStatus;
import com.example.userselfservice.Models.Token;
import com.example.userselfservice.Models.User;
import com.example.userselfservice.Services.UserService;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController  {
    private final TransactionAutoConfiguration.EnableTransactionManagementConfiguration.CglibAutoProxyConfiguration cglibAutoProxyConfiguration;
    private UserService userService;
    UserController(UserService userService, TransactionAutoConfiguration.EnableTransactionManagementConfiguration.CglibAutoProxyConfiguration cglibAutoProxyConfiguration) {
        this.userService = userService;
        this.cglibAutoProxyConfiguration = cglibAutoProxyConfiguration;
    }

    @PostMapping("/signup")
  public SignUpResponseDto signup(@RequestBody SignUpRequestDto requestDto) throws Exception{
        User user = userService.signup(
                requestDto.getEmail(),
                requestDto.getName(),
                requestDto.getPassword(),
                requestDto.getRoles()
        );

        return SignUpResponseDto.from(user);
    }

  @PostMapping("/login")
    public LogInResponseDto login(@RequestBody LogInRequestDto req) throws Exception{
        LogInResponseDto resDto = new LogInResponseDto();
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
  public ResponseEntity<Void> logout(@RequestBody LogOutRequestDto req){
    userService.logout(req.getToken());
    return new ResponseEntity<>(HttpStatus.OK);
  }

    @GetMapping("/validate/{token}")
    public SignUpResponseDto validateToken(@PathVariable String token) {
        System.out.println(token+ "TOkeneeee");
        User user = userService.validateToken(token);
        return SignUpResponseDto.from(user);
    }
}


