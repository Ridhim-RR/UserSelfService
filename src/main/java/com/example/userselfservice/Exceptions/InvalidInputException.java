package com.example.userselfservice.Exceptions;

import org.springframework.http.HttpStatus;

public class InvalidInputException extends Exception{
    private final HttpStatus status;

    public InvalidInputException(String msg, HttpStatus status){
        super(msg);
        this.status = status;
    }
    public HttpStatus getStatus(){
        return status;
    }
}
