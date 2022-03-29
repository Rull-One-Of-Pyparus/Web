package com.example.web.repos;


import com.example.web.Config.Entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role,Long> {
    Role findRoleByRole(String name);
}
