package com.example.rentify.service;

import com.example.rentify.model.User;
import com.example.rentify.repository.UserRepository;
import com.example.rentify.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public void registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Collections.singletonList(user.getRole())); // Assuming one role per user
        userRepository.save(user);
    }

    public String authenticateUser(User user) {
        User existingUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found with email " + user.getEmail()));

        if (!passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtUtil.generateToken(existingUser);
    }
}