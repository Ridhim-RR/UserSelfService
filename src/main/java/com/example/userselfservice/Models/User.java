package com.example.userselfservice.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.List;
@Entity
@Data
public class User extends BaseModel{
    private String Name;
    private String Email;
    private String hashedPassword;
    @ManyToMany
    private List<Roles> roles;
}
