package com.agritech.agritech_app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agritech.agritech_app.dto.UserRegistrationRequest;
import com.agritech.agritech_app.service.UserService;
import com.agritech.agritech_app.util.ValidationUtil;

@RestController
@RequestMapping("/api/authentication")
@Validated
public class UserAuthentication {

    private final UserService userService;

    public UserAuthentication(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Validated @RequestBody UserRegistrationRequest request) {
        // Validate email format
        if (!ValidationUtil.isValidEmail(request.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid email format");
        }

        // Register the user
        boolean isRegistered = userService.registerUser(request);
        if (isRegistered) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("User registered successfully");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("User already exists with the given email");
        }
    }
}
