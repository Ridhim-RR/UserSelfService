package com.example.userselfservice.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;
@Data
@Entity
public class Token extends BaseModel {
    private Date expiry;
    private String token;
    private boolean isValid;
    @ManyToOne
    private User user;
}
