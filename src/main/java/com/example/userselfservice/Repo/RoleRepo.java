package com.example.userselfservice.Repo;

import com.example.userselfservice.Models.Roles;
import org.springframework.data.repository.CrudRepository;

import javax.management.relation.Role;
import java.util.Optional;

public interface RoleRepo extends CrudRepository<Roles, Long> {
    Optional<Roles> findByRole(String name);

}
