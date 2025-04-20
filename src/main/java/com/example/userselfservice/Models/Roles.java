package com.example.userselfservice.Models;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Roles extends BaseModel {
    public Roles() {
    }

    public Roles(String role) {
        this.role = role;
    }
    private String role;

}
