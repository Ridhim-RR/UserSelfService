package com.example.userselfservice.Exceptions;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends Exception {
    private final HttpStatus status;

    public UserAlreadyExistsException(String msg, HttpStatus status) {
        super(msg);
        this.status = status;
    }
    public HttpStatus getStatus() {
        return status;
    }
}
