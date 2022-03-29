package com.example.web.repos;

import com.example.web.Config.Entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    boolean existsByUsername(String name);

    User findUserByUsername(String s);

}
