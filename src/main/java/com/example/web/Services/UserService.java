package com.example.web.Services;

import com.example.web.Config.Entity.Role;
import com.example.web.Config.Entity.User;
import com.example.web.Repository.RoleRepository;
import com.example.web.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    final private UserRepository userRepository;
    final private RoleRepository roleRepository;
    final private BCryptPasswordEncoder passwordEncoder;
    final AuthenticationManager authenticationManager;
    final UserDetailsService userDetailsService;


    public User userByName(String username) {
        return userRepository.findUserByUsername(username);
    }

    public void save(User user) {
        Collection<Role> roles = List.of(roleRepository.findRoleByRole("ROLE_USER"));
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

}

