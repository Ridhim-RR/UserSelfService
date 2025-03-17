package com.example.userselfservice.Dtos;

import lombok.Data;

@Data
public class SendEmailDto {
    private String to;
    private String subject;
    private String body;
    private String from;
}
