package com.agritech.agritech_app.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.agritech.agritech_app.dto.UserRegistrationRequest;
import com.agritech.agritech_app.entity.User;
import com.agritech.agritech_app.repositery.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoderService passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoderService passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean registerUser(UserRegistrationRequest request) {
        // Check if user already exists
        Optional<User> existingUser = userRepository.findByUsername(request.getUsername());
        if (existingUser.isPresent()) {
            return false; // User already exists
        }

        // Save new user
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return true;
    }
}
