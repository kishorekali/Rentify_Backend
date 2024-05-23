package com.example.rentify.controller;

import com.example.rentify.model.User;
import com.example.rentify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        userService.registerUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.authenticateUser(user));
    }

    @GetMapping("/hello")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello!");
    }
}