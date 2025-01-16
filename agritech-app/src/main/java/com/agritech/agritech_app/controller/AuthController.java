package com.agritech.agritech_app.controller;

import com.agritech.agritech_app.dto.LoginDTO;
import com.agritech.agritech_app.entity.User;
import com.agritech.agritech_app.repositery.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
public class AuthController {

    private final UserRepository userRepository;

    @Autowired
    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @PostMapping("/api/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginDTO loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        if ("user".equals(username) && "password".equals(password)) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Login successful");
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body(Map.of("message", "Invalid credentials vv"));
        }
    }
    
    @PostMapping("/api/signup")
    public ResponseEntity<Map<String, String>> signup(@RequestBody LoginDTO signupRequest) {
        String username = signupRequest.getUsername();
        String password = signupRequest.getPassword();

        User user = new User();
        user.setUsername(username);  // Use the values from request instead of hardcoded values
        user.setPassword(password);

        if (userRepository.save(user) != null) {  // Save user to the repository
            Map<String, String> response = new HashMap<>();
            response.put("message", "Signup successful");
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body(Map.of("message", "Signup failed!"));
        }
    }
}
