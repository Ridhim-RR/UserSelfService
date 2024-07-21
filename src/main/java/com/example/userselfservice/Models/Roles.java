package com.example.userselfservice.Models;

import jakarta.persistence.Entity;
import lombok.Data;

import java.util.List;
@Data
@Entity
public class Roles extends BaseModel {
    private String role;

}
